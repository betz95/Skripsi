package engine;


import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ermengarde
 */
public abstract class Element {
    protected String name;
    protected HashMap<String, String> attributes;
    
    public Element(String name, String attributes) {
        this.name = name;
        this.attributes = new HashMap<String, String>();
        String att[] = attributes.split("\"");
        String attributeName = "";
        String attributeValue = "";
        if(!attributes.isEmpty()){
            for(int i=0;i<att.length;i+=2){
                attributeName = att[i].substring(0, att[i].length()-1);
                if(this.name.equals("path") && attributeName.equals("d")){
                    int attLen = att[i+1].length();
                    String newAtt = "";
                    Character cur = null;
                    for(int j=0;j<attLen;j++){
                        cur = att[i+1].charAt(j);
                        if(Character.isAlphabetic(cur)){
                            newAtt += " " + cur + " ";
                        }
                        else{
                            if(cur == '-'){
                                newAtt += " " + cur;
                            }
                            else{
                                newAtt += cur;
                            }
                        }
                    }
                    newAtt = newAtt.trim();
                    attributeValue = newAtt;
                }
                else{
                    attributeValue = att[i+1];
                }
                this.attributes.put(attributeName, attributeValue);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
    
    private double getHorizontalLength(){
        return this.getMaxX() - this.getMinX();
    }
    
    private double getVerticalLength(){
        return this.getMaxY() - this.getMinY();
    }
    
    public double getBoundingRectArea(){
        if(this.getHorizontalLength()==0)return this.getVerticalLength();
        if(this.getVerticalLength()==0)return this.getHorizontalLength();
        return this.getHorizontalLength() * this.getVerticalLength();
    }

    public abstract double getMaxX();

    public abstract double getMinX();

    public abstract double getMaxY();

    public abstract double getMinY();
}
