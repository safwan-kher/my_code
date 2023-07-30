/**
public class Main {
  public static void main(String[] args) {
    // Create a sample beam
    Beam beam = new Beam(10.0); // Beam length = 10.0 meters

    // Add supports
    beam.addSupport(new Support(Support.SupportType.FIXED));
    beam.addSupport(new Support(Support.SupportType.PINNED));

    // Add loads
    beam.addLoad(new Load(Load.LoadType.UNIFORM_LOAD, 20.0)); // Magnitude of 20.0 kN/m
    beam.addLoad(new Load(Load.LoadType.POINT_LOAD, 50.0)); // Magnitude of 50.0 kN

    // Set cross-section properties
    CrossSection crossSection = new CrossSection(100.0, 5000.0);
    beam.setCrossSection(crossSection);

    // Calculate support forces
    Calculation.calculateSupportForces(beam);

    // Calculate internal forces
    Calculation.calculateInternalForces(beam);

    // Calculate deflection at a specific position
    double position = 5.0; // Position in meters
    double deflection = Calculation.calculateDeflection(beam, position);
    System.out.println("Deflection at position " + position + " meters: " + deflection + " meters");

    // Select the optimum cross-section
    CrossSection optimumCrossSection = Calculation.selectOptimumCrossSection(beam);
    System.out.println("Optimum Cross-Section: Area = " + optimumCrossSection.getArea()
        + " mm^2, Moment of Inertia = " + optimumCrossSection.getMomentOfInertia() + " mm^4");

    // Export the beam model to a text file
    Calculation.exportBeamModel(beam, "beam_model.txt");

    // Import the beam model from a text file
    Beam importedBeam = Calculation.importBeamModel("beam_model.yaml");
    System.out.println("Beam Model Imported Successfully.");
  }
}
**/

public class Main {
  public static void main(String[] args) {
    // Create a sample beam
    Beam beam = new Beam(10.0); // Beam length = 10.0 meters

    // Add supports
    beam.addSupport(new Support(Support.SupportType.FIXED));
    beam.addSupport(new Support(Support.SupportType.PINNED));

    // Add loads
    beam.addLoad(new Load(Load.LoadType.UNIFORM_LOAD, 20.0)); // Magnitude of 20.0 kN/m
    beam.addLoad(new Load(Load.LoadType.POINT_LOAD, 50.0)); // Magnitude of 50.0 kN

    // Set cross-section properties
    CrossSection crossSection = new CrossSection(100.0, 5000.0);
    beam.setCrossSection(crossSection);

    // Calculate support forces
    Calculation.calculateSupportForces(beam);

    // Calculate internal forces
    Calculation.calculateInternalForces(beam);

    // Calculate deflection at a specific position
    double position = 5.0; // Position in meters
    double deflection = Calculation.calculateDeflection(beam, position);
    System.out.println("Deflection at position " + position + " meters: " + deflection + " meters");

    // Select the optimum cross-section
    CrossSection optimumCrossSection = Calculation.selectOptimumCrossSection(beam);
    System.out.println("Optimum Cross-Section: Area = " + optimumCrossSection.getArea()
        + " mm^2, Moment of Inertia = " + optimumCrossSection.getMomentOfInertia() + " mm^4");

    // Export the beam model to a text file
    Calculation.exportBeamModel(beam, "beam_model.txt");

    // Import the beam model from a text file
    Beam importedBeam = Calculation.importBeamModel("beam_model.txt");
    System.out.println("Beam Model Imported Successfully.");

    // Display the imported beam information
    System.out.println("Imported Beam Length: " + importedBeam.getLength() + " meters");
    System.out.println("Imported Supports: ");
    for (Support support : importedBeam.getSupports()) {
      System.out.println(support.getType() + ", " + support.getReactionForceX() + ", "
          + support.getReactionForceY() + ", " + support.getMoment());
    }
    System.out.println("Imported Loads: ");
    for (Load load : importedBeam.getLoads()) {
      System.out.println(load.getType() + ", " + load.getMagnitude());
    }
    System.out.println("Imported Cross-Section: Area = " + importedBeam.getCrossSection().getArea()
        + " mm^2, Moment of Inertia = " + importedBeam.getCrossSection().getMomentOfInertia() + " mm^4");
  }
}
