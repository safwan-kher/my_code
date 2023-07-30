import java.util.ArrayList;
import java.util.List;

public class Beam {
  // Beam attributes
  private double length;
  private List<Support> supports;
  private List<Load> loads;
  private CrossSection crossSection;

  public Beam(double length) {
    this.length = length;
    this.supports = new ArrayList<>();
    this.loads = new ArrayList<>();
  }

  // Methods to add supports and loads to the beam
  public void addSupport(Support support) {
    supports.add(support);
  }

  public void addLoad(Load load) {
    loads.add(load);
  }

  // Getters and setters for attributes
  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public List<Support> getSupports() {
    return supports;
  }

  public void setSupports(List<Support> supports) {
    this.supports = supports;
  }

  public List<Load> getLoads() {
    return loads;
  }

  public void setLoads(List<Load> loads) {
    this.loads = loads;
  }

  public CrossSection getCrossSection() {
    return crossSection;
  }

  public void setCrossSection(CrossSection crossSection) {
    this.crossSection = crossSection;
  }
}

