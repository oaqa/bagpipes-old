/*
 *  Copyright 2012 Carnegie Mellon University
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package edu.cmu.lti.oaqa.cse.component.uima;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import mx.bigdata.anyobject.AnyObject;
import mx.bigdata.anyobject.AnyTuple;

import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.UimaContextAdmin;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.resource.CustomResourceSpecifier;
import org.apache.uima.resource.Resource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.impl.CustomResourceSpecifier_impl;
import org.apache.uima.resource.metadata.TypePriorities;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.uimafit.factory.AnalysisEngineFactory;
import org.uimafit.factory.CollectionReaderFactory;
import org.uimafit.factory.TypePrioritiesFactory;
import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import edu.cmu.lti.oaqa.cse.ResourceHandle;
import edu.cmu.lti.oaqa.cse.ResourceHandle.HandleType;
import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.PersistenceProviderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.parameter.IntegerParameter;
import edu.cmu.lti.oaqa.cse.configuration.parameter.Parameter;
import edu.cmu.lti.oaqa.cse.configuration.parameter.StringParameter;
import edu.cmu.lti.oaqa.ecd.config.ConfigurationLoader;
import edu.cmu.lti.oaqa.ecd.impl.AbstractExperimentPersistenceProvider;
import edu.cmu.lti.oaqa.ecd.impl.DefaultExperimentPersistenceProvider;

public class ExperimentBuilder {

  private static final Set<String> FILTER = ImmutableSet.of("class", "inherit", "pipeline");

  private static final ResourceHandle NOOP_RESOURCE = ResourceHandle.newInheritHandle("base.noop");

  public static final String EXPERIMENT_UUID_PROPERTY = "cse.driver.experiment.uuid";

  public static final String STAGE_ID_PROPERTY = "cse.driver.experiment.stageIdd";

  private final TypeSystemDescription typeSystem;

  private final String experimentUuid;

  // private final AnyObject configuration;

  private AbstractExperimentPersistenceProvider persistence;

  private TypePriorities typePriorities = (TypePriorities) null;

  public ExperimentBuilder(String experimentUuid, String resource, TypeSystemDescription typeSystem)
          throws Exception {
    this.typeSystem = typeSystem;
    this.experimentUuid = experimentUuid;
    // this.configuration = ConfigurationLoader.load(resource);
  }

  public String getExperimentUuid() {
    return experimentUuid;
  }

  public AnalysisEngine createNoOpEngine() throws Exception {
    Map<String, Object> tuples = Maps.newLinkedHashMap();
    Class<? extends AnalysisComponent> ac = loadFromClassOrInherit(NOOP_RESOURCE,
            AnalysisComponent.class, tuples);
    AnalysisEngineDescription aeDesc = createAnalysisEngineDescription(tuples, ac);
    System.out.println("\t- " + aeDesc.getAnalysisEngineMetaData().getName());
    return produceAnalysisEngine(null, aeDesc);
  }

  // public AnalysisEngine buildPipeline(AnyObject config, String pipeline,
  // int stageId, FixedFlow funnel) throws Exception {
  // try {
  // return buildPipeline(config, pipeline, stageId, funnel, false);
  // } catch (Exception e) {
  // Throwables.propagateIfInstanceOf(e,
  // ResourceInitializationException.class);
  // throw new ResourceInitializationException(
  // ResourceInitializationException.ERROR_INITIALIZING_FROM_DESCRIPTOR,
  // new Object[] { pipeline, config }, e);
  // }
  // }

  // public AnalysisEngine buildPipeline(AnyObject config, String pipeline,
  // int stageId, FixedFlow funnel, boolean outputNewCASes)
  // throws Exception {
  // loadTypePriorities(config);
  // Iterable<AnyObject> iterable = config.getIterable(pipeline);
  // FlowControllerDescription fcd = FlowControllerFactory
  // .createFlowControllerDescription(FixedFlowController797182.class);
  // AnalysisEngineDescription aee = buildPipeline(stageId, iterable, fcd);
  // if (funnel != null) {
  // FixedFlow fc = (FixedFlow) aee.getAnalysisEngineMetaData()
  // .getFlowConstraints();
  // funnel.setFixedFlow(fc.getFixedFlow());
  // aee.getAnalysisEngineMetaData().setFlowConstraints(funnel);
  // }
  // aee.getAnalysisEngineMetaData().getOperationalProperties()
  // .setOutputsNewCASes(outputNewCASes);
  // aee.getAnalysisEngineMetaData().setName(pipeline);
  // return AnalysisEngineFactory.createAggregate(aee);
  // }

  // public AnalysisEngine buildPipeline(AnyObject config, String pipeline,
  // int stageId) throws Exception {
  // try {
  // return buildPipeline(config, pipeline, stageId, null, false);
  // } catch (Exception e) {
  // throw new ResourceInitializationException(
  // ResourceInitializationException.ERROR_INITIALIZING_FROM_DESCRIPTOR,
  // new Object[] { pipeline, config }, e);
  // }
  // }

  // private AnalysisEngineDescription buildPipeline(int stageId,
  // Iterable<AnyObject> pipeline, FlowControllerDescription fcd)
  // throws Exception {
  // AggregateBuilder builder = new AggregateBuilder(null, null, fcd);
  // int phase = 1;
  // for (AnyObject aeDescription : pipeline) {
  // AnalysisEngineDescription description = buildComponent(stageId,
  // phase, aeDescription);
  // builder.add(description);
  // phase++;
  // }
  // return builder.createAggregateDescription();
  // }

  private String[] getFromListOrInherit(AnyObject descriptor, String listName) throws IOException {
    Iterable<String> iterable = descriptor.getIterable(listName);
    if (iterable != null) {
      ArrayList<String> typePrioritiesList = new ArrayList<String>();
      for (String type : iterable) {
        typePrioritiesList.add(type);
        System.out.println("Loaded type priorities: " + type);
      }
      return typePrioritiesList.toArray(new String[0]);
    } else {
      String resource = descriptor.getString("inherit");
      if (resource != null) {
        AnyObject yaml = ConfigurationLoader.load(resource);
        return getFromListOrInherit(yaml, listName);
      } else {
        throw new IllegalArgumentException(
                "Illegal experiment descriptor, must contain one list of type <" + listName
                        + "> or <inherit>");
      }
    }
  }

  // Load type priorities
  private void loadTypePriorities(AnyObject config) {
    AnyObject tpObject = config.getAnyObject("type-priorities");
    if (tpObject == null) {
      return;
    }
    try {
      String[] typePrioritiesArray = getFromListOrInherit(tpObject, "type-list");
      this.typePriorities = TypePrioritiesFactory.createTypePriorities(typePrioritiesArray);
    } catch (IOException e) {
      System.err.println("Failed to load type-priorities.");
      e.printStackTrace();
    }
  }

  // Made this method public to invoke it from BasePhaseTest
  public AnalysisEngineDescription buildComponent(ComponentDescriptor descriptor) throws Exception {
    Class<? extends AnalysisComponent> ac = Class.forName(descriptor.getClassName()).asSubclass(
            AnalysisComponent.class);
    Map<String, Parameter> parameters = descriptor.getParamMap();
    parameters.put(EXPERIMENT_UUID_PROPERTY, new StringParameter(EXPERIMENT_UUID_PROPERTY,
            experimentUuid));
    parameters.put(STAGE_ID_PROPERTY, new IntegerParameter(STAGE_ID_PROPERTY, 0));
    Object[] params = getParamArray(parameters);
    AnalysisEngineDescription description = AnalysisEngineFactory.createPrimitiveDescription(ac,
            typeSystem, typePriorities, params);
    // Parameter name = parameters.get("name");
    // description.getAnalysisEngineMetaData().setName(name.toString());
    return description;
  }

  public CollectionReader buildCollectionReader(CollectionReaderDescriptor descriptor)
          throws Exception {
    Class<? extends CollectionReader> readerClass = Class.forName(descriptor.getClassName())
            .asSubclass(CollectionReader.class);
    Map<String, Parameter> parameters = descriptor.getParamMap();
    parameters.put(EXPERIMENT_UUID_PROPERTY, new StringParameter(EXPERIMENT_UUID_PROPERTY,
            experimentUuid));
    parameters.put(STAGE_ID_PROPERTY, new IntegerParameter(STAGE_ID_PROPERTY, 0));
    Object[] params = getParamArray(parameters);
    CollectionReader reader = CollectionReaderFactory.createCollectionReader(readerClass,
            typeSystem, params);
    return reader;
  }

  private void insertExperiment(AnyObject config, String resource) throws Exception {
    //AnyObject experiment = config.getAnyObject("configuration");
    //String name = experiment.getString("name");
    //String author = experiment.getString("author");
    persistence.insertExperiment(getExperimentUuid(), "bagpipes", "bagpipes",
            "", "");
  }

  public static <T extends Resource> T loadProvider(String provider, Class<T> type)
          throws ResourceInitializationException {
    Yaml yaml = new Yaml();
    @SuppressWarnings("unchecked")
    Map<String, String> map = (Map<String, String>) yaml.load(provider);
    ResourceHandle handle = buildHandleFromMap(map);
    try {
      return buildResource(handle, type);
    } catch (Exception e) {
      throw new ResourceInitializationException(
              ResourceInitializationException.ERROR_INITIALIZING_FROM_DESCRIPTOR, new Object[] {
                  type.getCanonicalName(),
                  provider }, e);
    }
  }

  public static <T extends Resource> T loadProvider(AnyObject ao, Class<T> type)
          throws ResourceInitializationException {
    ResourceHandle handle = buildHandleFromObject(ao);
    try {
      return buildResource(handle, type);
    } catch (Exception e) {
      throw new ResourceInitializationException(
              ResourceInitializationException.ERROR_INITIALIZING_FROM_DESCRIPTOR, new Object[] {
                  type.getCanonicalName(),
                  ao }, e);
    }
  }

  public static AnalysisEngine[] createAnnotators(String description) {
    Yaml yaml = new Yaml();
    @SuppressWarnings("unchecked")
    List<Map<String, String>> names = (List<Map<String, String>>) yaml.load(description);
    List<AnalysisEngine> annotators = Lists.newArrayList();
    for (Map<String, String> name : names) {
      try {
        Map<String, Object> tuples = Maps.newHashMap();
        ResourceHandle handle = buildHandleFromMap(name);
        Class<? extends JCasAnnotator_ImplBase> aClass = loadFromClassOrInherit(handle,
                JCasAnnotator_ImplBase.class, tuples);
        Object[] params = getParamList(tuples);
        AnalysisEngineDescription aeDesc = AnalysisEngineFactory.createPrimitiveDescription(aClass,
                params);
        annotators.add(UIMAFramework.produceAnalysisEngine(aeDesc));
      } catch (Exception e) {
        System.err.printf("[ERROR] %s Caused by:\n", e);
        Throwables.getRootCause(e).printStackTrace();
      }
    }
    return annotators.toArray(new AnalysisEngine[0]);
  }

  public static <T extends Resource> List<T> createResourceList(List<Map<String, String>> names,
          Class<T> type) {
    List<T> resources = Lists.newArrayList();
    for (Map<String, String> name : names) {
      try {
        ResourceHandle handle = buildHandleFromMap(name);
        resources.add(buildResource(handle, type));
      } catch (Exception e) {
        System.err.printf("[ERROR] %s Caused by:\n", e);
        Throwables.getRootCause(e).printStackTrace();
      }
    }
    return resources;
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
                  type.getCanonicalName(),
                  handle }, e);
    }
  }

  private static <T extends Resource> T buildResource(Class<? extends Resource> resourceClass,
          Map<String, Object> tuples, Class<T> type) throws Exception {
    CustomResourceSpecifier spec = new CustomResourceSpecifier_impl();
    spec.setResourceClassName(resourceClass.getName());
    Resource resource = UIMAFramework.produceResource(spec, tuples);
    return type.cast(resource);
  }

  public static Object[] getParamList(Map<String, Object> tuples) {
    List<Object> params = Lists.newArrayList();
    for (Map.Entry<String, Object> me : tuples.entrySet()) {
      params.add(me.getKey());
      params.add(cast(me.getValue()));
    }
    return params.toArray();
  }

  public static Object[] getParamArray(Map<String, Parameter> tuples) {
    List<Object> params = Lists.newArrayList();
    for (Map.Entry<String, Parameter> me : tuples.entrySet()) {
      params.add(me.getKey());
      params.add(cast(me.getValue().getVal()));
    }
    return params.toArray();
  }

  private static Object cast(Object o) {
    if (o instanceof Iterable) {
      List<?> list = Lists.newArrayList((Iterable<?>) o);
      Object k = list.get(0);
      if (k instanceof String) {
        return list.toArray(new String[0]);
      } else if (k instanceof Integer) {
        return list.toArray(new Integer[0]);
      } else if (k instanceof Float) {
        return list.toArray(new Float[0]);
      } else if (k instanceof Double) {
        List<Float> bogus = Lists.newArrayList();
        for (Object j : list) {
          bogus.add(((Double) j).floatValue());
        }
        return bogus.toArray(new Float[0]);
      } else if (list.get(0) instanceof Boolean) {
        return list.toArray(new Boolean[0]);
      }
    } else if (o instanceof Double) {
      return ((Double) o).floatValue();
    }
    return o;
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

  public static AnalysisEngineDescription createAnalysisEngineDescription(
          Map<String, Object> tuples, Class<? extends AnalysisComponent> comp)
          throws ResourceInitializationException {
    Object[] params = getParamList(tuples);
    AnalysisEngineDescription aeDesc = AnalysisEngineFactory.createPrimitiveDescription(comp,
            params);
    StringBuilder sb = new StringBuilder(comp.getSimpleName());
    if (params.length > 0) {
      appendMethodSignature(sb, tuples);
    }
    String name = sb.toString().replaceAll("\n", " ").trim();
    aeDesc.getAnalysisEngineMetaData().setName(name);
    return aeDesc;

  }

  public static AnalysisEngine produceAnalysisEngine(@Nullable
  UimaContext c, AnalysisEngineDescription aeDesc) throws ResourceInitializationException {
    AnalysisEngine ae;
    if (c != null) {
      ae = UIMAFramework.produceAnalysisEngine(aeDesc, ((UimaContextAdmin) c).getResourceManager(),
              null);
    } else {
      ae = UIMAFramework.produceAnalysisEngine(aeDesc);
    }
    return ae;
  }

  public static <T> T createFromName(String name, Class<T> type) throws ClassNotFoundException,
          InstantiationException, IllegalAccessException {
    Class<? extends T> clazz = Class.forName(name).asSubclass(type);
    return clazz.newInstance();
  }

  private static ResourceHandle buildHandleFromString(String name) {
    String[] values = name.split("!");
    return ResourceHandle.newHandle(values[0], values[1]);
  }

  public static ResourceHandle buildHandleFromMap(Map<String, String> map) {
    Map.Entry<String, String> name = Iterators.get(map.entrySet().iterator(), 0);
    return ResourceHandle.newHandle(name.getKey(), name.getValue());
  }

  public static ResourceHandle buildHandleFromObject(AnyObject object) {
    AnyTuple name = Iterables.get(object.getTuples(), 0);
    return ResourceHandle.newHandle(name.getKey(), (String) name.getObject());
  }

  private static void appendMethodSignature(StringBuilder sb, Map<String, Object> tuples) {
    Map<String, String> ftuples = Maps.transformValues(tuples, new ObjectFormatter());
    sb.append("[");
    Joiner.MapJoiner joiner = Joiner.on("#").withKeyValueSeparator(":");
    joiner.appendTo(sb, ftuples);
    sb.append("]");
  }

  private final static class ObjectFormatter implements Function<Object, String> {

    @Override
    public String apply(Object o) {
      if (o instanceof Iterable) {
        Joiner joiner = Joiner.on("#");
        return joiner.join((Iterable<?>) o).toString();
      }
      return o.toString();
    }

  }

  public static <T extends Resource> List<T> createResourceList(Object o, Class<T> type) {
    List<T> resources = null;
    if (o instanceof String) {
      String description = (String) o;
      Yaml yaml = new Yaml();
      @SuppressWarnings("unchecked")
      List<Map<String, String>> ao = (List<Map<String, String>>) yaml.load(description);
      resources = createResourceList(ao, type);
    } else {
      // TODO: Remove this deprecated call at some point in time
      String[] expListenerNames = (String[]) o;
      resources = createResourceList(expListenerNames, type);
    }
    return resources;
  }

  private <T extends Resource> T initializeResource(PersistenceProviderDescriptor descriptor,
          String node, Class<T> type) throws Exception { 
    Class<? extends Resource> ac = Class.forName(descriptor.getClassName()).asSubclass(
                              Resource.class);
    Map<String, Object> params = getParamMap(descriptor.getParamMap());
    return buildResource(ac, params, type);
  }

  public static Map<String, Object> getParamMap(Map<String, Parameter> tuples) {
    Map<String, Object> params = Maps.newHashMap();
    for (Map.Entry<String, Parameter> me : tuples.entrySet()) {
      params.put(me.getKey(), cast(me.getValue().getVal()));
    }
    return params;
  }
  
  public void initializePersistenceProvider(PersistenceProviderDescriptor config) throws Exception {
      this.persistence = newPersistenceProvider(config);
      insertExperiment(null, null);
  }

  private AbstractExperimentPersistenceProvider newPersistenceProvider(
          PersistenceProviderDescriptor config) throws ResourceInitializationException {
    if (config == null) {
      return new DefaultExperimentPersistenceProvider();
    }
    try {
      return initializeResource(config, "persistence-provider",
              AbstractExperimentPersistenceProvider.class);
    } catch (Exception e) {
      throw new ResourceInitializationException(
              ResourceInitializationException.ERROR_INITIALIZING_FROM_DESCRIPTOR, new Object[] {
                  "persistence-provider",
                  config }, e);
    }
  }

}
