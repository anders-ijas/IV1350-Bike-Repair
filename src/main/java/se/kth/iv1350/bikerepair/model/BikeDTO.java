package se.kth.iv1350.bikerepair.model;

public class BikeDTO {
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

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
