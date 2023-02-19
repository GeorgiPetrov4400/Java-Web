package softuni.pathfinder.model.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;
import softuni.pathfinder.model.enums.CategoryName;
import softuni.pathfinder.model.enums.Level;

import java.util.Set;

public class RouteAddBindingModel {

    @Size(min = 3, max = 20, message = "Route name must be between 3 and 20 characters!")
    private String name;

    @Size(min = 3)
    private String description;

    private MultipartFile gpxCoordinates;

    @NotNull
    private Level level;

    private String videoUrl;

    private Set<CategoryName> categories;

    public RouteAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<CategoryName> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryName> categories) {
        this.categories = categories;
    }
}
