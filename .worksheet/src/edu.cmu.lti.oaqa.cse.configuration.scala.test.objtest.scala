package edu.cmu.lti.oaqa.cse.configuration.scala.test

object objtest {
 
class A(a: String, b:String) {

	def update(a:String = this.a,b:String = this.b) : A = new A(a,b)
	override def toString() = a+ " " + b
}

object A{
def apply(a:String,b:String) = new A(a,b)
};import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(295); 



val a :A = A("foo","bar");System.out.println("""a  : edu.cmu.lti.oaqa.cse.configuration.scala.test.objtest.A = """ + $show(a ));$skip(3); val res$0 = 

a;System.out.println("""res0: edu.cmu.lti.oaqa.cse.configuration.scala.test.objtest.A = """ + $show(res$0));$skip(17); val res$1 = 

a.update("baz");System.out.println("""res1: edu.cmu.lti.oaqa.cse.configuration.scala.test.objtest.A = """ + $show(res$1))}


}
