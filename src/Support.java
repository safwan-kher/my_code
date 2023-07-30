public class Support {
  // Support types
  public enum SupportType {
    FIXED,
    PINNED,
    ROLLER
  }

  private SupportType type;
  private double reactionForceX;
  private double reactionForceY;
  private double moment;

  public Support(SupportType type) {
    this.type = type;
  }

  public Support(SupportType type, double reactionForceX, double reactionForceY, double moment) {
    this.type = type;
    this.reactionForceX = reactionForceX;
    this.reactionForceY = reactionForceY;
    this.moment = moment;
  }

  // Getters and setters for attributes
  public SupportType getType() {
    return type;
  }

  public void setType(SupportType type) {
    this.type = type;
  }

  public double getReactionForceX() {
    return reactionForceX;
  }

  public void setReactionForceX(double reactionForceX) {
    this.reactionForceX = reactionForceX;
  }

  public double getReactionForceY() {
    return reactionForceY;
  }

  public void setReactionForceY(double reactionForceY) {
    this.reactionForceY = reactionForceY;
  }

  public double getMoment() {
    return moment;
  }

  public void setMoment(double moment) {
    this.moment = moment;
  }
}
