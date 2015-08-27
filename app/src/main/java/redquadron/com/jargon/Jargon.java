package redquadron.com.jargon;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Jargon")
public class Jargon extends ParseObject implements Comparable<Jargon>{
    public Jargon(){

    }

    public boolean isCompleted(){
        return getBoolean("completed");
    }

    public void setCompleted(boolean complete){
        put("completed", complete);
    }

    public String getStack(){
        return getString("stack");
    }

    public void setStack(String stack){
        put("stack", stack);
    }

    public String getText(){
        return getString("text");
    }

    public void setText(String text){
        put("text", text);
    }

    public String getDescription(){
        return getString("description");
    }

    public void setDescription(String description){
        put("description", description);
    }

    public String getSourceName(){
        return getString("sourceName");
    }

    public void setSourceName(String sourceName){
        put("sourceName", sourceName);
    }

    public String getSourceLocation(){
        return getString("sourceLocation");
    }

    public void setSourceLocation(String sourceLocation){
        put("sourceLocation", sourceLocation);
    }

    @Override
    public int compareTo(Jargon another) {
        return 0;
    }
}