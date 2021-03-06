package textgen;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		List<String> words = getTokens(sourceText, "[a-zA-Z.,]+");
		if (words.isEmpty()) {
			return;
		}
		starter = words.get(0);
		String prevWord = starter;
		for (int i = 1; i < words.size(); i++) {
			String w = words.get(i);
			ListNode currWordNode = null;
			for (ListNode node: wordList) {
				if (prevWord.equals(node.getWord())) {
					currWordNode = node;
				}
			}
			if (currWordNode != null) {
				currWordNode.addNextWord(w);
			} else {
				ListNode newNode = new ListNode(prevWord);
				newNode.addNextWord(w);
				wordList.add(newNode);
			}
			prevWord = w;
		}
		prevWord = words.get(words.size() - 1);
		ListNode currWordNode = null;
		for (ListNode node: wordList) {
			if (prevWord.equals(node.getWord())) {
				currWordNode = node;
				break;
			}
		}
		if (currWordNode != null) {
			currWordNode.addNextWord(starter);
		} else {
			ListNode newNode = new ListNode(prevWord);
			newNode.addNextWord(starter);
			wordList.add(newNode);
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if (wordList == null || wordList.size() == 0 || numWords == 0) {
			return "";
		}
		String currWord = starter;
		StringBuilder output = new StringBuilder();
		output.append(currWord);
		numWords --;
		while (numWords > 0) {
			ListNode wordNode = null;
			for (ListNode node: wordList) {
				if (currWord.equals(node.getWord())) {
					wordNode = node;
					break;
				}
			}
			currWord = wordNode.getRandomNextWord(rnGenerator);
			output.append(" ").append(currWord);
			numWords--;
		}
		return output.toString();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		StringBuilder toReturn = new StringBuilder();
		for (ListNode n : wordList)
		{
			toReturn.append(n.toString());
		}
		return toReturn.toString();
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList.clear();
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	protected List<String> getTokens(String text, String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));

		String textString0 = "hi there hi Leo";
		System.out.println(textString0);
		gen.train(textString0);
		System.out.println(gen);
		System.out.println(gen.generateText(4));

		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.retrain(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int randIndex = generator.nextInt(nextWords.size());
	    return nextWords.get(randIndex);
	}

	public String toString()
	{
		StringBuilder toReturn = new StringBuilder(word + ": ");
		for (String s : nextWords) {
			toReturn.append(s).append("->");
		}
		toReturn.append("\n");
		return toReturn.toString();
	}
	
}


