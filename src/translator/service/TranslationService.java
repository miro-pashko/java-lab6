package translator.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Everything about English-Ukrainian translation lives behind this one
 * class. Callers never see the HashMap, never see how punctuation is
 * handled, never see how an unknown word is flagged - they get two verbs:
 * teach it a word, hand it a phrase.
 */
public class TranslationService {

    private final Map<String, String> glossary = new HashMap<>();

    public TranslationService() {
        seedDefaults();
    }

    private void seedDefaults() {
        addTranslation("hello", "привіт");
        addTranslation("world", "світ");
        addTranslation("good", "добрий");
        addTranslation("morning", "ранок");
        addTranslation("friend", "друг");
        addTranslation("book", "книга");
        addTranslation("water", "вода");
        addTranslation("house", "дім");
        addTranslation("yes", "так");
        addTranslation("no", "ні");
        addTranslation("bad", "поганий");
        addTranslation("day", "день");
        addTranslation("evening", "вечір");
        addTranslation("person", "людина");
        addTranslation("people", "люди");
        addTranslation("child", "дитина");
        addTranslation("kid", "дитина");
        addTranslation("children", "діти");
        addTranslation("kids", "діти");
        addTranslation("house", "будинок");
        addTranslation("home", "вдома");
        addTranslation("in", "у");
        addTranslation("on", "на");
        addTranslation("at", "в");
        addTranslation("place", "місце");
        addTranslation("food", "їжа");
        addTranslation("juice", "сік");
        addTranslation("milk", "молоко");
        addTranslation("name", "ім'я");
        addTranslation("my", "мій");
        addTranslation("do", "робити");
        addTranslation("work", "робота");
        addTranslation("bread", "хліб");
    }

    public void addTranslation(String english, String ukrainian) {
        glossary.put(normalize(english), ukrainian);
    }

    public boolean knows(String english) {
        return glossary.containsKey(normalize(english));
    }

    public int vocabularySize() {
        return glossary.size();
    }

    /** For display purposes only - callers can't mutate the real glossary through this. */
    public Map<String, String> snapshot() {
        return Collections.unmodifiableMap(glossary);
    }

    public String translate(String phrase) {
        if (phrase == null || phrase.isBlank()) {
            return "";
        }
        return Arrays.stream(phrase.trim().split("\\s+"))
                .map(this::translateWord)
                .collect(Collectors.joining(" "));
    }

    // Walks in from both ends of the token to peel off punctuation, so
    // "friend!" and "(world)" still match "friend" and "world" while the
    // punctuation itself survives untouched in the output.
    private String translateWord(String token) {
        int start = 0;
        while (start < token.length() && !Character.isLetterOrDigit(token.charAt(start))) {
            start++;
        }
        int end = token.length();
        while (end > start && !Character.isLetterOrDigit(token.charAt(end - 1))) {
            end--;
        }
        if (start == end) {
            return token;
        }

        String core = token.substring(start, end);
        String found = glossary.get(normalize(core));
        if (found == null) {
            return token.substring(0, start) + "{" + core + "}" + token.substring(end);
        }
        return token.substring(0, start) + found + token.substring(end);
    }

    private String normalize(String word) {
        return word.toLowerCase();
    }
}
