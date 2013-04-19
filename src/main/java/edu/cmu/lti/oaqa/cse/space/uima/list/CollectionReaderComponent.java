package edu.cmu.lti.oaqa.cse.space.uima.list;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.CasCreationUtils;

import com.google.common.collect.Lists;

public class CollectionReaderComponent extends UimaComponent {

  private final CollectionReader reader;

  public CollectionReaderComponent(CollectionReader reader) {
    super(reader.getMetaData().getName());
    this.reader = reader;
  }

  @Override
  public void init() {
    // TODO Auto-generated method stub
  }

  @Override
  public List<JCas> execute(List<JCas> input) throws Exception {
    List<JCas> list = Lists.newArrayList();
    while (reader.hasNext()) {
      CAS cas = CasCreationUtils.createCas(asList(reader.getMetaData()));
      reader.getNext(cas);
      list.add(cas.getJCas());
    }
    return list;
  }

}
