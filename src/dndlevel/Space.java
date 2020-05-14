package dndlevel;

public abstract class Space {
    /**
     * @return description of the space.
     */
    public abstract String getDescription();

    /**
     * @param theDoor adds door.
     */
    public abstract void setDoor(Door theDoor);
}
