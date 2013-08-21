package edu.cmu.lti.oaqa.cse.scala.space

import edu.cmu.lti.oaqa.cse.scala.configuration._

/**
 * Provides mapping between configuration descriptor to configuration space.
 *    *      *
 * Example:
 * The following configuration descriptor,
 * {{{
 * (<-----------------ConfigurationDescriptor----------------->)
 *                    (<------Phases----->)
 * collection-reader   p_0     p_1     p_2     standalone
 * -----------------  ------  ------  ------  -----------
 * 		                      c_1_0
 *                                    c_2_0
 * 		   cr         c_0_0   c_1_1               s_0
 *                                    c_2_1
 * 		                      c_1_2
 * }}}
 *
 *
 * results in,
 *
 * {{{
 * collection-reader:         cr
 *                            +
 * p_0:                      c_0
 *             +--------------+--------------+
 * p_1:      c_1_0          c_1_1          c_1_2
 *         +------+       +------+       +------+
 * p_2:  c_2_0  c_2_1   c_2_0  c_2_1   c_2_0  c_2_1
 *         +      +       +      +       +       +
 * std:   s_0    s_0     s_0    s_0     s_0     s_0
 * }}}
 *
 */
object ConfigurationSpace {

  /**
   * n-ary tree to store the configuration space.
   */
  sealed abstract class Tree[T]
  case class Node[T](elem: T, children: List[Tree[T]] = Nil) extends Tree[T]
  case class Leaf[T](elem: T) extends Tree[T]

  /**
   *  Returns a new [[$packagePath.ConfigurationSpace.Tree]] describing all possible
   *  execution paths given by a [[$packaPath.ConfigurationDescripor]].
   *  @param confDes
   *    The configuration descriptor describing the `ConfigurationSpace`.
   *
   */
  def populateTree(confDes: ConfigurationDescriptor): Tree[ExecutableConf] = {
    /**
     * INNER FUNCTION:
     * Returns the children of the root node (expanding the entire discrete version
     * of the tree).
     *
     * Iterates over arbitrary list of ExecutableDescriptor, expanding
     * [[$packagePath.PhaseDescriptor]] to its tree representation.
     */
    def populateTree(execs: List[ExecutableConf]): List[Tree[ExecutableConf]] = execs match {
      //(1) pipeline is empty, should consider failing on this.
      case Nil => Nil
      //(2) last element is a phase, expand phase to its list of options as leaves. 
      //   (reached end of pipeline)
      case PhaseDescriptor(_, options) :: Nil => options.map(Leaf[ExecutableConf](_))
      //(3) last element is a component, return component in a leaf. (reached end of pipeline)
      case head :: Nil => Leaf(head) :: Nil
      //(4) element is a phase, expand phase to its list of options, 
      //recursively populating the rest  of the tree, and setting the resulting 
      //tree(s) as the children of each option in the phase
      case PhaseDescriptor(_, options) :: tail => options.map(Node(_, populateTree(tail)))
      //(5) element is a component, store standalone component in node, 
      // recursively populating the rest of the tree setting the resulting 
      // tree(s) as the children of the node
      case head :: tail => Node(head, populateTree(tail)) :: Nil
    }

    //begin populating tree by expanding the root node containing the collection-reader.
    confDes match {
      case ConfigurationDescriptor(_, collectionReader, pipeline) => Node[ExecutableConf](collectionReader, populateTree(pipeline))
    }
  }
}