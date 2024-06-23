
public class OffsetData {
  int lineOffset;
  int charOffset;

  public OffsetData(int lineOffset, int charOffset) {
    this.lineOffset = lineOffset;
    this.charOffset = charOffset;
  }

  @Override
  public String toString() {
    return "line=" + lineOffset+ ",charOffset="+ charOffset+'\''+ '\n';
  }
}
