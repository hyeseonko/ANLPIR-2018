
/* First created by JCasGen Wed Nov 23 17:36:58 CET 2016 */
package it.unitn.nlpir.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Wed Nov 23 17:36:58 CET 2016
 * @generated */
public class LdaTopicDistribution_Type extends TOP_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (LdaTopicDistribution_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = LdaTopicDistribution_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new LdaTopicDistribution(addr, LdaTopicDistribution_Type.this);
  			   LdaTopicDistribution_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new LdaTopicDistribution(addr, LdaTopicDistribution_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = LdaTopicDistribution.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("it.unitn.nlpir.types.LdaTopicDistribution");
 
  /** @generated */
  final Feature casFeat_topicDistribution;
  /** @generated */
  final int     casFeatCode_topicDistribution;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTopicDistribution(int addr) {
        if (featOkTst && casFeat_topicDistribution == null)
      jcas.throwFeatMissing("topicDistribution", "it.unitn.nlpir.types.LdaTopicDistribution");
    return ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTopicDistribution(int addr, int v) {
        if (featOkTst && casFeat_topicDistribution == null)
      jcas.throwFeatMissing("topicDistribution", "it.unitn.nlpir.types.LdaTopicDistribution");
    ll_cas.ll_setRefValue(addr, casFeatCode_topicDistribution, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public double getTopicDistribution(int addr, int i) {
        if (featOkTst && casFeat_topicDistribution == null)
      jcas.throwFeatMissing("topicDistribution", "it.unitn.nlpir.types.LdaTopicDistribution");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i);
	return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setTopicDistribution(int addr, int i, double v) {
        if (featOkTst && casFeat_topicDistribution == null)
      jcas.throwFeatMissing("topicDistribution", "it.unitn.nlpir.types.LdaTopicDistribution");
    if (lowLevelTypeChecks)
      ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i);
    ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_topicDistribution), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public LdaTopicDistribution_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_topicDistribution = jcas.getRequiredFeatureDE(casType, "topicDistribution", "uima.cas.DoubleArray", featOkTst);
    casFeatCode_topicDistribution  = (null == casFeat_topicDistribution) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_topicDistribution).getCode();

  }
}



    