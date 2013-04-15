package edu.cmu.lti.oaqa.cse.space.uima;

import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;

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
    reader.getNext(input.getCas());
    return input;
  }
	
}
