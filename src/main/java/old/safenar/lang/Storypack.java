package old.safenar.lang;

import old.safenar.Main;

import java.io.File;
import java.nio.file.Files;

public class Storypack{
    private File configFile;
    private String name;
    private String author;
    private String version;
    private String description;
    private Storypack[] dependencies;
    private String[] scripts;
    private String[] languages;
    private String[] resources;

    public Storypack(File configFile){
        this.configFile = configFile;
        parseConfig();
    }

    public Storypack(String name) {
        this.name = name;
    }

    private void parseConfig(){
        /* parse config file and set variables accordingly
        * config file should be in the following format:
        * {
        *  "name": "Storypack Name",
        *  "author": "Author Name",
        *  "version": "1.0",
        *  "description": "Description of the storypack",
        *  "dependencies": ["Storypack Name", "Storypack Name"],
        *  "scripts": ["script1.mbf", "script2.mbf"],
        *  "languages": ["en", "es"],
        *  "resources": ["res1.png", "res2.png"],
        * }
        */
        if(configFile.exists()){
            try{
                String[] lines = Files.readAllLines(configFile.toPath()).toArray(new String[0]);
                for(String line : lines){
                    if(line.startsWith("name")){
                        name = line.split(":")[1].trim();
                    }else if(line.startsWith("author")){
                        author = line.split(":")[1].trim();
                    }else if(line.startsWith("version")){
                        version = line.split(":")[1].trim();
                    }else if(line.startsWith("description")){
                        description = line.split(":")[1].trim();
                    }else if(line.startsWith("dependencies")){
                        String[] dependencies = line.split(":")[1].trim().split(",");
                        this.dependencies = new Storypack[dependencies.length];
                        for(int i = 0; i < dependencies.length; i++){
                            this.dependencies[i] = old.safenar.Main.getStorypack(dependencies[i].trim());
                        }
                    }else if(line.startsWith("scripts")){
                        scripts = line.split(":")[1].trim().split(",");
                    }else if(line.startsWith("languages")){
                        languages = line.split(":")[1].trim().split(",");
                    }else if(line.startsWith("resources")){
                        resources = line.split(":")[1].trim().split(",");
                    }
                }
            }
            catch(Exception e){
                old.safenar.Main.logger.log(Main.logger.getStackTrace(e));
            }
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public Storypack[] getDependencies() {
        return dependencies;
    }

    public String[] getScripts() {
        return scripts;
    }

    public String[] getLanguages() {
        return languages;
    }

    public String[] getResources() {
        return resources;
    }

    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDependencies(Storypack[] dependencies) {
        this.dependencies = dependencies;
    }

    public void setScripts(String[] scripts) {
        this.scripts = scripts;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public void setResources(String[] resources) {
        this.resources = resources;
    }

    public boolean isDependency(Storypack storypack){
        for(Storypack dep : dependencies){
            if(dep.getName().equals(storypack.getName())){
                return true;
            }
        }
        return false;
    }
}
