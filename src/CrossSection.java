public class CrossSection {
  private double area; // Cross-sectional area of the beam
  private double momentOfInertia; // Moment of inertia of the beam

  public CrossSection(double area, double momentOfInertia) {
    this.area = area;
    this.momentOfInertia = momentOfInertia;
  }

  // Getters and setters for attributes
  public double getArea() {
    return area;
  }

  public void setArea(double area) {
    this.area = area;
  }

  public double getMomentOfInertia() {
    return momentOfInertia;
  }

  public void setMomentOfInertia(double momentOfInertia) {
    this.momentOfInertia = momentOfInertia;
  }
}
