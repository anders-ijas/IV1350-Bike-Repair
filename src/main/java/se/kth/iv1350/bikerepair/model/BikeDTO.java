package se.kth.iv1350.bikerepair.model;

/**
 * A DTO object to transfer information between MVC layers.
 */
public final class BikeDTO {
    private final String brand;
    private final String model;
    private final String serialNumber;


    /**
     * Creates a new instance.
     * @param brand The brand of the bike
     * @param model The bike model
     * @param serialNumber The bike serial number
     */
    public BikeDTO(String brand, String model, String serialNumber) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the brand of the bike
     * @return String
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the model of the bike
     * @return String
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the serial number of the bike
     * @return String
     */
    public String getSerialNumber() {
        return serialNumber;
    }
}
