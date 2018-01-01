package fr.univlyon1.tiw1.tp3.controller;

import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.tp3.modele.MarchandiseEntity;
import fr.univlyon1.tiw1.tp3.service.MarchandiseService;
import fr.univlyon1.tiw1.tp3.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/2/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarchandiseControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private MarchandiseService marchandiseService;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getTest() throws Exception {

        //Integer reference, String nom, double volumeUnitaire, String description
        Marchandise marchandise = new MarchandiseEntity(1, "marchandise1 ", 20,
                "marchandise1");
        Mockito.when(marchandiseService.getByReference(Mockito.anyInt()))
                .thenReturn(marchandise);

        RequestBuilder requestBuilder = get(
                "/marchandise/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"nom\":\"marchandise1 \",\"volumeUnitaire\":20.0,\"description\":\"marchandise1\",\"reference\":1}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
        assert(result.getResponse().getStatus() == HttpStatus.OK.value());

    }

    @Test
    public void getAllTest() throws Exception {
        List<Marchandise> marchandiseList = Arrays.asList(
                new MarchandiseEntity(1, "marchandise1 ", 10,
                        "marchandise1"),
                new MarchandiseEntity(2, "marchandise2 ", 25,
                        "marchandise2"));

        Mockito.when(marchandiseService.getAll()).thenReturn(marchandiseList);

        RequestBuilder requestBuilder = get("/marchandise").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"nom\":\"marchandise1 \",\"volumeUnitaire\":10.0,\"description\":\"marchandise1\",\"reference\":1}, " +
                "{\"nom\":\"marchandise2 \",\"volumeUnitaire\":25.0,\"description\":\"marchandise2\",\"reference\":2}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assert(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void postTest() throws Exception {
        Marchandise marchandise = new MarchandiseEntity(1, "marchandise1 ", 10,
                "marchandise1");

//        Mockito.when(marchandiseService.getByRef(marchandise.getReference())).thenReturn(marchandise);
        Mockito.when(marchandiseService.createOrUpdate(marchandise)).thenReturn(marchandise);

        RequestBuilder requestBuilder = post("/marchandise")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.asJsonString(marchandise));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"nom\":\"marchandise1 \",\"volumeUnitaire\":10.0,\"description\":\"marchandise1\",\"reference\":1}";
        JSONAssert.assertEquals(result.getResponse().getContentAsString(), expected, false);
        assert(result.getResponse().getStatus() == HttpStatus.CREATED.value());
    }

    @Test
    public void putTest() throws Exception {
        Marchandise marchandise = new MarchandiseEntity(1, "marchandise1 ", 10,
                "marchandise1");

        Mockito.when(marchandiseService.getByRef(marchandise.getReference())).thenReturn(marchandise);
        Mockito.when(marchandiseService.createOrUpdate(marchandise)).thenReturn(marchandise);

        RequestBuilder requestBuilder = put("/marchandise/{ref}", marchandise.getReference())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.asJsonString(marchandise));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"nom\":\"marchandise1 \",\"volumeUnitaire\":10.0,\"description\":\"marchandise1\",\"reference\":1}";
        JSONAssert.assertEquals(result.getResponse().getContentAsString(), expected, false);
        assert(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void deleteTest() throws Exception {
        Marchandise marchandise = new MarchandiseEntity(1, "marchandise1 ", 10,
                "marchandise1");
        Mockito.when(marchandiseService.getByRef(marchandise.getReference())).thenReturn(marchandise);
        Mockito.doNothing().when(marchandiseService).remove(marchandise.getReference());

        RequestBuilder requestBuilder = delete("/marchandise/{ref}", marchandise.getReference())
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.asJsonString(marchandise));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assert(result.getResponse().getContentLength() == 0);
        assert(result.getResponse().getStatus() == HttpStatus.NO_CONTENT.value());
    }

}
