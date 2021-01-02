package Day6_POJO;

public class RegionPost {

    private String region_name;
    private int region_id;

    public RegionPost(int region_id, String region_name) {
        this.region_id = region_id;
        this.region_name = region_name;
    }
    public RegionPost(){}

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }


    @Override
    public String toString() {
        return "RegionPost{" +
                "region_id=" + region_id +
                ", region_name='" + region_name + '\'' +
                '}';
    }



}
