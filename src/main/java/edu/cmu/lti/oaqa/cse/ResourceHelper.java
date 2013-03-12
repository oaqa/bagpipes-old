package edu.cmu.lti.oaqa.cse;

import java.util.Map;
import java.util.Set;

import mx.bigdata.anyobject.AnyObject;
import mx.bigdata.anyobject.AnyTuple;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.CustomResourceSpecifier;
import org.apache.uima.resource.Resource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.impl.CustomResourceSpecifier_impl;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

import edu.cmu.lti.oaqa.cse.ResourceHandle.HandleType;
import edu.cmu.lti.oaqa.ecd.config.ConfigurationLoader;

public class ResourceHelper {

  private static final Set<String> FILTER = ImmutableSet.of("class", "inherit", "pipeline");
 
  public static <T extends Resource> T initializeResource(AnyObject config, String node, Class<T> type)
          throws Exception {
    AnyObject descriptor = config.getAnyObject(node);
    if (descriptor == null) {
      return null;
    }
    Map<String, Object> tuples = Maps.newLinkedHashMap();
    Class<? extends Resource> cseClass = getFromClassOrInherit(descriptor, Resource.class, tuples);
    return buildResource(cseClass, tuples, type);
  }
  

  public static <T extends Resource> T buildResource(ResourceHandle handle, Class<T> type)
          throws ResourceInitializationException {
    Map<String, Object> tuples = Maps.newLinkedHashMap();
    try {
      Class<? extends Resource> resourceClass = loadFromClassOrInherit(handle, Resource.class,
              tuples);
      return buildResource(resourceClass, tuples, type);
    } catch (Exception e) {
      throw new ResourceInitializationException(
              ResourceInitializationException.ERROR_INITIALIZING_FROM_DESCRIPTOR, new Object[] {
                  type.getCanonicalName(), handle }, e);
    }
  }

  private static <T extends Resource> T buildResource(Class<? extends Resource> resourceClass,
          Map<String, Object> tuples, Class<T> type) throws Exception {
    CustomResourceSpecifier spec = new CustomResourceSpecifier_impl();
    spec.setResourceClassName(resourceClass.getName());
    Resource resource = UIMAFramework.produceResource(spec, tuples);
    return type.cast(resource);
  }
  
  public static <C> Class<? extends C> loadFromClassOrInherit(ResourceHandle resource,
          Class<C> ifaceClass, Map<String, Object> tuples) throws Exception {
    if (resource.getType() == HandleType.CLASS) {
      return Class.forName(resource.resource).asSubclass(ifaceClass);
    } else {
      if (resource.getType() == HandleType.INHERIT) {
        AnyObject yaml = ConfigurationLoader.load(resource.resource);
        return getFromClassOrInherit(yaml, ifaceClass, tuples);
      } else {
        throw new IllegalArgumentException(
                "Illegal experiment descriptor, must contain one node of type <class> or <inherit>");
      }
    }
  }

  public static <C> Class<? extends C> getFromClassOrInherit(AnyObject descriptor,
          Class<C> ifaceClass, Map<String, Object> tuples) throws Exception {
    for (AnyTuple tuple : descriptor.getTuples()) {
      if (!FILTER.contains(tuple.getKey())) {
        if (!tuples.containsKey(tuple.getKey())) {
          tuples.put(tuple.getKey(), tuple.getObject());
        }
      }
    }
    String name = descriptor.getString("class");
    if (name != null) {
      return Class.forName(name).asSubclass(ifaceClass);
    } else {
      String resource = descriptor.getString("inherit");
      if (resource != null) {
        AnyObject yaml = ConfigurationLoader.load(resource);
        return getFromClassOrInherit(yaml, ifaceClass, tuples);
      } else {
        throw new IllegalArgumentException(
                "Illegal experiment descriptor, must contain one node of type <class> or <inherit>");
      }
    }
  }
}
