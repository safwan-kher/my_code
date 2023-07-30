import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Calculation {
  // Constants
  private static final double GRAVITY = 9.81; // Acceleration due to gravity

  // Method to calculate support forces
  public static void calculateSupportForces(Beam beam) {
    // Check if the beam has supports
    if (beam.getSupports().isEmpty()) {
      System.out.println("Error: Beam has no supports!");
      return;
    }

    double totalLoadX = 0;
    double totalLoadY = 0;

    // Calculate total applied loads
    for (Load load : beam.getLoads()) {
      switch (load.getType()) {
        case POINT_LOAD:
        case UNIFORM_LOAD:
          totalLoadY += load.getMagnitude();
          break;
        case TRIANGULAR_LOAD:
          totalLoadY += (2 * load.getMagnitude()) / 3;
          break;
      }
    }

    // Calculate support forces
    for (Support support : beam.getSupports()) {
      switch (support.getType()) {
        case FIXED:
          support.setReactionForceX(0);
          support.setReactionForceY(totalLoadY);
          support.setMoment(0);
          break;
        case PINNED:
          support.setReactionForceX(0);
          support.setReactionForceY(totalLoadY / 2);
          support.setMoment(0);
          break;
        case ROLLER:
          support.setReactionForceX(0);
          support.setReactionForceY(totalLoadY);
          support.setMoment(0);
          break;
      }
    }
  }

  // Method to calculate internal forces (simplified version)
  public static void calculateInternalForces(Beam beam) {
    // Implementation of internal force calculations can be quite complex.
    // This is a simplified version for demonstration purposes.

    // Assuming constant cross-section properties for simplicity
    double area = beam.getCrossSection().getArea();
    double momentOfInertia = beam.getCrossSection().getMomentOfInertia();

    // Calculate internal forces at different positions along the beam
    double position;
    for (position = 0; position <= beam.getLength(); position += 0.1) {
      double shearForce = -10; // Simplified value for shear force
      double moment = 20 * position; // Simplified value for bending moment

      // Store the calculated internal forces at this position for further analysis
      // You can create appropriate data structures to store the results as needed.
    }
  }

  // Method to calculate deflection (simplified version)
  public static double calculateDeflection(Beam beam, double position) {
    // Implementation of deflection calculation can be quite complex.
    // This is a simplified version for demonstration purposes.

    double deflection = 0;

    // Assuming constant cross-section properties for simplicity
    double momentOfInertia = beam.getCrossSection().getMomentOfInertia();

    // Calculate deflection at the specified position using a simplified equation
    deflection = (5 * GRAVITY * beam.getLength() * position * position) / (384 * momentOfInertia);

    return deflection;
  }

  // Method to calculate stresses (simplified version)
  public static double calculateStress(Beam beam, double position, double momentOfInertia) {
    // Implementation of stress calculation can be quite complex.
    // This is a simplified version for demonstration purposes.

    double stress = 0;

    // Assuming constant cross-section properties for simplicity
    double area = beam.getCrossSection().getArea();
    double moment = 20 * position; // Simplified value for bending moment

    // Calculate stress at the specified position using a simplified equation
    stress = moment * (beam.getLength() / 2) / (area * momentOfInertia);

    return stress;
  }


  // Method to import the beam model from a text file
  public static Beam importBeamModel(String fileName) {
    Beam importedBeam = null;

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;

      // Read beam length
      line = reader.readLine();
      double beamLength = Double.parseDouble(line.substring(line.indexOf(":") + 2));

      // Create the beam object
      importedBeam = new Beam(beamLength);

      // Read supports
      line = reader.readLine(); // Skip the line containing "Supports:"
      while (!(line = reader.readLine()).equals("Loads:")) {
        String[] supportData = line.split(", ");
        Support.SupportType supportType = Support.SupportType.valueOf(supportData[0]);
        double reactionForceX = Double.parseDouble(supportData[1]);
        double reactionForceY = Double.parseDouble(supportData[2]);
        double moment = Double.parseDouble(supportData[3]);

        Support support = new Support(supportType, reactionForceX, reactionForceY, moment);
        importedBeam.addSupport(support);
      }

      // Read loads
      while (!(line = reader.readLine()).startsWith("Cross-Section:")) {
        if (line.trim().isEmpty()) continue; // Skip empty lines
        String[] loadData = line.split(", ");
        Load.LoadType loadType = Load.LoadType.valueOf(loadData[0]);
        double magnitude = Double.parseDouble(loadData[1]);

        Load load = new Load(loadType, magnitude);
        importedBeam.addLoad(load);
      }

      // Read cross-section properties
     // line = reader.readLine();
      if (line.startsWith("Cross-Section:")) {
        String[] crossSectionData = line.substring(line.indexOf(":") + 1).split(", ");
        double area = Double.parseDouble(crossSectionData[0].trim());
        double momentOfInertia = Double.parseDouble(crossSectionData[1].trim());

        CrossSection crossSection = new CrossSection(area, momentOfInertia);
        importedBeam.setCrossSection(crossSection);
      }

    } catch (IOException e) {
      System.out.println("Error importing beam model: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println("Error parsing load type: " + e.getMessage());
    }

    return importedBeam;
  }

  // Method to export the beam model to a text file
  public static void exportBeamModel(Beam beam, String fileName) {
    try (FileWriter writer = new FileWriter(fileName)) {
      // Write beam length
      writer.write("Beam Length: " + beam.getLength() + "\n");

      // Write supports
      writer.write("Supports:\n");
      for (Support support : beam.getSupports()) {
        writer.write(support.getType() + ", " + support.getReactionForceX() + ", "
            + support.getReactionForceY() + ", " + support.getMoment() + "\n");
      }

      // Write loads
      writer.write("Loads:\n");
      for (Load load : beam.getLoads()) {
        writer.write(load.getType() + ", " + load.getMagnitude() + "\n");
      }

      // Write cross-section properties
      CrossSection crossSection = beam.getCrossSection();
      writer.write("Cross-Section: " + crossSection.getArea() + ", " + crossSection.getMomentOfInertia() + "\n");
    } catch (IOException e) {
      System.out.println("Error exporting beam model: " + e.getMessage());
    }
  }

  // Method to select the optimum cross-section for the beam based on minimum weight
  public static CrossSection selectOptimumCrossSection(Beam beam) {
    // Calculate internal forces in the beam
    Calculation.calculateInternalForces(beam);

    // Assume initial minimum weight as positive infinity
    double minWeight = Double.POSITIVE_INFINITY;
    CrossSection optimumCrossSection = null;

    // Iterate over different cross-sections and find the one with the minimum weight
    for (double area = 100.0; area <= 5000.0; area += 100.0) {
      for (double momentOfInertia = 1000.0; momentOfInertia <= 50000.0; momentOfInertia += 1000.0) {
        CrossSection crossSection = new CrossSection(area, momentOfInertia);
        beam.setCrossSection(crossSection);

        // Calculate the weight of the beam for the current cross-section
        double weight = beam.getLength() * crossSection.getArea();

        // Check if the current weight is lower than the current minimum weight
        if (weight < minWeight) {
          minWeight = weight;
          optimumCrossSection = crossSection;
        }
      }
    }

    return optimumCrossSection;
  }
}
