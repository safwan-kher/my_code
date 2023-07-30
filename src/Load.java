public class Load {
  // Load types
  public enum LoadType {
    POINT_LOAD,
    UNIFORM_LOAD,
    TRIANGULAR_LOAD
  }

  private LoadType type;
  private double magnitude;

  public Load(LoadType type, double magnitude) {
    this.type = type;
    this.magnitude = magnitude;
  }

  // Getters and setters for attributes
  public LoadType getType() {
    return type;
  }

  public void setType(LoadType type) {
    this.type = type;
  }

  public double getMagnitude() {
    return magnitude;
  }

  public void setMagnitude(double magnitude) {
    this.magnitude = magnitude;
  }
}
