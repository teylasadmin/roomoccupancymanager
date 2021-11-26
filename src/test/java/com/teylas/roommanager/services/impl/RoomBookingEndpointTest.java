package com.teylas.roommanager.services.impl;

import com.teylas.roommanager.controllers.RoomBookingController;
import com.teylas.roommanager.services.RoomBooking;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomBookingController.class)
public class RoomBookingEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomBooking roomBooking;

    @Test
    @Ignore("To be completed")
    public void shouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(post("/room-manager/profit-per-type")
                    .content("")
                    .contentType(MediaType.TEXT_PLAIN_VALUE)
                    .accept(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isBadRequest());
    }
}
