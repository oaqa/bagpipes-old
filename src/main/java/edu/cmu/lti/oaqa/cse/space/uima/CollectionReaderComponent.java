package edu.cmu.lti.oaqa.cse.space.uima;

import static java.util.Arrays.asList;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.CasCreationUtils;

public class CollectionReaderComponent extends UimaComponent {

 private final CollectionReader reader;
  
  public CollectionReaderComponent(CollectionReader reader){
    super(reader.getMetaData().getName());
    this.reader = reader;
  }
  
  @Override
  public void init() {
    // TODO Auto-generated method stub
  }

  @Override
  public JCas execute(JCas input) throws Exception {
    CAS cas = CasCreationUtils.createCas(asList(reader.getMetaData()));
    reader.getNext(cas);
    return cas.getJCas();
  }
	
}
