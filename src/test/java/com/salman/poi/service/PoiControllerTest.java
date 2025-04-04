package com.salman.poi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salman.poi.service.dto.AddressDTO;
import com.salman.poi.service.dto.LocationDTO;
import com.salman.poi.service.dto.PoiDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PoiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllPois() throws Exception {
        mockMvc.perform(get("/api/pois"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreatePoi() throws Exception {
        PoiDTO poiDTO = getPoiDTO();

        mockMvc.perform(post("/api/pois")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(poiDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Location"))
                .andExpect(jsonPath("$.category").value("Test Category"));
    }

    private static PoiDTO getPoiDTO() {
        PoiDTO poiDTO = new PoiDTO();
        poiDTO.setId(10000L);
        poiDTO.setName("Test Location");
        poiDTO.setCategory("Test Category");

        AddressDTO address = new AddressDTO();
        address.setStreet("Test Street");
        address.setCity("Test City");
        address.setCountry("Test Country");
        address.setPostalCode("00000");

        LocationDTO location = new LocationDTO();
        location.setLatitude(12.3456);
        location.setLongitude(65.4321);
        address.setLocation(location);

        poiDTO.setAddress(address);
        poiDTO.setComments(new ArrayList<>());
        return poiDTO;
    }
}