package it.unitn.nlpir.experiment.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import it.uniroma2.sag.kelp.utils.JacksonSerializerWrapper;
import it.unitn.nlpir.nodematchers.FocusEntityNodeMatcher;
import it.unitn.nlpir.nodematchers.HardNodeMatcher;
import it.unitn.nlpir.nodematchers.NodeMatcher;
import it.unitn.nlpir.nodematchers.strategies.MatchingStrategy;
import it.unitn.nlpir.tree.ITreePostprocessor;
import it.unitn.nlpir.tree.TreeBuilder;
import it.unitn.nlpir.util.wiki.IAnnotationsCache;
import it.unitn.nlpir.util.wiki.IFeatureCache;

public class ExperimentComponentFactory {
	
	
	public static final String TYPE_QC_NER_MATCH="typeFocus";
	public static final String ADD_FOCUS_TO_QUESTION="addFocusToQ";
	public static final String TRUE_STRING="true";
	
	
	public static final String FEATURE_CACHE_TYPE = "feature_cache_type";
	public static final String ANNOTATION_CACHE_TYPE = "annotation_cache_type";
	public static final String DBNAME= "dbname";
	public static final String DB_ANNOTATION_COLLECTION= "db_annotation_collection";
	public static final String DB_FEATURE_CACHE_COLLECTION= "db_feature_cache_collection";
	public static final String MONGODB_HOST = "mongodb_host";
	
	public static final String MONGODB_PORT = "mongodb_port";
	public static final String WIKI_CACHE_CLASS = "wiki_cache_class";
	public static final String FEATURE_CACHE_CLASS = "feature_cache_class";
	public static final String BIDIRECTIONAL = "bidirectional";
	
	public static final String MONGODB_CACHE_TYPE = "mongodb";
	
	public static final String REL_LABEL = "rellabel";
	
	public static final String DB_ANNOTATION_COLLECTION_PROPERTY="db_annotation_collection";
	
	public static MatchingStrategy getMatchingStrategy(String matchingStrategyClassName) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> c;
		c = Class.forName(matchingStrategyClassName).getConstructor();
		MatchingStrategy mc = (MatchingStrategy) c.newInstance();
		return mc;
	}
	
	public static NodeMatcher initializeRelMatcher(MatcherConfig mc) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		NodeMatcher matcher = new HardNodeMatcher(getMatchingStrategy(mc.getMatchingStrategyClassName()));
		return matcher;
	}
	
	
	public static TreeBuilder getTreeBuilder(String treeBuilderClassName) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> c;
		c = Class.forName(treeBuilderClassName).getConstructor();
		TreeBuilder mc = (TreeBuilder) c.newInstance();
		return mc;
	}
	
	public static ITreePostprocessor getTreePostprocessor(String treePostprocessorClassName) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> c;
		c = Class.forName(treePostprocessorClassName).getConstructor();
		ITreePostprocessor mc = (ITreePostprocessor) c.newInstance();
		return mc;
	}
	
	
 
	
	public static NodeMatcher initializeTMFocusMatcher(MatcherConfig mc, Properties p) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		IAnnotationsCache annotationsCache = null;
		if (mc.getParameterValue(ANNOTATION_CACHE_TYPE).equals(MONGODB_CACHE_TYPE)) {
			Constructor<?> c;
			
			String annotationCollection = mc.getParameterValue(DB_ANNOTATION_COLLECTION);
			if (p.containsKey(DB_ANNOTATION_COLLECTION_PROPERTY))
				annotationCollection = p.getProperty(DB_ANNOTATION_COLLECTION_PROPERTY);			
			c = Class.forName(mc.getParameterValue(WIKI_CACHE_CLASS)).getConstructor(String.class, String.class, int.class, String.class);
			annotationsCache = (IAnnotationsCache) c.newInstance(mc.getParameterValue(DBNAME), 
					mc.getParameterValue(MONGODB_HOST), Integer.valueOf(mc.getParameterValue(MONGODB_PORT)),
					annotationCollection);
		}
		
		
		IFeatureCache featuresCache=null;
		if (mc.getParameterValue(FEATURE_CACHE_TYPE).equals(MONGODB_CACHE_TYPE)) {
			Constructor<?> c;
			c = Class.forName(mc.getParameterValue(FEATURE_CACHE_CLASS)).getConstructor(String.class, String.class, int.class, String.class);
			featuresCache = (IFeatureCache) c.newInstance(mc.getParameterValue(DBNAME), mc.getParameterValue(MONGODB_HOST), Integer.valueOf(mc.getParameterValue(MONGODB_PORT)),
					mc.getParameterValue(DB_FEATURE_CACHE_COLLECTION));
		}
		
		
		Constructor<?> c;
		c = p.containsKey(REL_LABEL) ? 
				Class.forName(mc.getMatcherClass()).getConstructor(String.class,MatchingStrategy.class, IAnnotationsCache.class, IFeatureCache.class, boolean.class) :
					Class.forName(mc.getMatcherClass()).getConstructor(MatchingStrategy.class, IAnnotationsCache.class, IFeatureCache.class, boolean.class);
		
				NodeMatcher matcher = p.containsKey(REL_LABEL) ?
				(NodeMatcher) c.newInstance((String)p.get(REL_LABEL),getMatchingStrategy(mc.getMatchingStrategyClassName()), annotationsCache, featuresCache, mc.getParameterValue(BIDIRECTIONAL).equals(TRUE_STRING)): 
					(NodeMatcher) c.newInstance(getMatchingStrategy(mc.getMatchingStrategyClassName()),  annotationsCache, featuresCache, mc.getParameterValue(BIDIRECTIONAL).equals(TRUE_STRING));
		return matcher;
	}

	public static NodeMatcher initializeRelFocusMatcher(MatcherConfig mc) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		NodeMatcher matcher = new FocusEntityNodeMatcher(getMatchingStrategy(mc.getMatchingStrategyClassName()),
				mc.getParameterValue(TYPE_QC_NER_MATCH).equals(TRUE_STRING),
				mc.getParameterValue(ADD_FOCUS_TO_QUESTION).equals(TRUE_STRING));
		return matcher;
	}
	

	public static NodeMatcher createNewNodeMatcher(String pathToTheMatcherJsonDescriptor) throws JsonParseException, JsonMappingException, IOException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		return createNewNodeMatcher(pathToTheMatcherJsonDescriptor,null);
	}
	
	public static NodeMatcher createNewNodeMatcher(String pathToTheMatcherJsonDescriptor, Properties p) throws JsonParseException, JsonMappingException, IOException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		JacksonSerializerWrapper jw = new JacksonSerializerWrapper();
		MatcherConfig mc = jw.readValue(new File(pathToTheMatcherJsonDescriptor), MatcherConfig.class);
		
		if (mc.getMatcherType().equals(MatcherConfig.REL_MATCHER_TYPE)) {
			return initializeRelMatcher(mc);
		}
		if (mc.getMatcherType().equals(MatcherConfig.REL_FOCUS_MATCHER_TYPE)) {
			return initializeRelFocusMatcher(mc);
		}
		if (mc.getMatcherType().equals(MatcherConfig.TM_MATCHER_TYPE)) {
			return initializeTMFocusMatcher(mc,p);
		}
		return null;
	}
}
