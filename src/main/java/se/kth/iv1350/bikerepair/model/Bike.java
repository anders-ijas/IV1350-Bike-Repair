package se.kth.iv1350.bikerepair.model;

/**
 * A customers bike, a customer can have several bikes
 */
public class Bike {
    private final String brand;
    private final String model;
    private final String serialNumber;


    /**
     * Creates a new instance.
     * @param brand What brand the bike has
     * @param model What model the bike has
     * @param serialNumber What serial number the bike has
     */
    public Bike(String brand, String model, String serialNumber) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    /**
     * Creates a DTO to transfer information between layers
     * @return BikeDTO
     */
    public BikeDTO createDTO() {
        return new BikeDTO(brand, model, serialNumber);
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
