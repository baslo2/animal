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
}
