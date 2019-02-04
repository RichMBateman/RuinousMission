package com.bateman.richard.model;

public class ActionCard {
    public String type;
    public String definition;
    public String accuracy;
    public String flavorText;
    public String name;

    private ActionCard (Builder b) {
        this.type = b.type;
        this.definition = b.definition;
        this.accuracy = b.accuracy;
        this.flavorText = b.flavorText;
        this.name = b.name;
    }

    @Override
    public String toString() {
        return "type: " + this.type + "\r\n"
                + "definition: " + this.definition + "\r\n"
                + "accuracy: " + this.accuracy + "\r\n"
                + "flavorText: " + this.flavorText + "\r\n"
                + "name: " + this.name + "\r\n";
    }

    public static class Builder {
        private String type;
        private String definition;
        private String accuracy;
        private String flavorText;
        private String name;

        public Builder setName(String name) {this.name = name; return this;}
        public Builder setType(String type) {this.type = type; return this;}
        public Builder setDefinition(String definition) {this.definition = definition; return this;}
        public Builder setAccuracy(String accuracy) {this.accuracy = accuracy; return this;}
        public Builder setFlavorText(String flavorText) {this.flavorText = flavorText; return this;}

        public ActionCard build() {
            return new ActionCard(this);
        }
    }
}
