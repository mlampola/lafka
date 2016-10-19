package fi.lampola.lafka.googleplaces;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingResponse {

    private List<Place> results;
    private String status;
    
    public List<Place> getResults() {
        return results;
    }
    
    public void setResults(List<Place> results) {
        this.results = results;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Status: " + status + "\n");
        if (null != results) {
            sb.append("Places:\n");
            for (Place place: results) {
                sb.append(place.toString() + "\n");
            }
        }
        return sb.toString();
    }
}

