package home.handlers;

public enum Tag {

    TYPE("type"),
    AGE("age"),
    NAME("name"),
    ANIMALS("animals");

    private final String tagName;

    Tag(String tagName){
        this.tagName = tagName;
    }

    public String getTagName(){
        return tagName;
    }

    public static Tag getTag(String tagName){
        String tagFormatted = tagName.strip();
        for (Tag tag : Tag.values()) {
            if (tagFormatted.equals(tag.getTagName())) {
                return tag;
            }
        }
        return null;
    }
}
