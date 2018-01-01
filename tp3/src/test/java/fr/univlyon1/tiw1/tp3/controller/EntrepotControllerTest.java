package fr.univlyon1.tiw1.tp3.controller;

import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.tp3.modele.EntrepotEntity;
import fr.univlyon1.tiw1.tp3.service.EntrepotService;
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
 * @since 1.0 11/30/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepotControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private EntrepotService entrepotService;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getTest() throws Exception {

        EntrepotEntity mktEntrepot = new EntrepotEntity("test", 50);
        Mockito.when(entrepotService.getByName(Mockito.anyString()))
                .thenReturn(mktEntrepot);

        RequestBuilder requestBuilder = get(
                "/entrepot/test").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{nom:test,capacite:50.0,marchandisesStockees:[],occupation:0.0}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
        assert(result.getResponse().getStatus() == HttpStatus.OK.value());

    }

    @Test
    public void getAllTest() throws Exception {
        List<Entrepot> entrepotList = Arrays.asList(
                new EntrepotEntity("entrepot1", 10),
                new EntrepotEntity("entrepot2", 50));

        Mockito.when(entrepotService.getAll()).thenReturn(entrepotList);

        RequestBuilder requestBuilder = get("/entrepot/").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{nom:entrepot1,capacite:10.0,marchandisesStockees:[],occupation:0.0}, " +
                "{nom:entrepot2,capacite:50.0,marchandisesStockees:[],occupation:0.0}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assert(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void postTest() throws Exception {
        Entrepot entrepot = new EntrepotEntity("entrepot1", 50);

        Mockito.when(entrepotService.getByNom(entrepot.getNom())).thenReturn(entrepot);
        Mockito.doNothing().when(entrepotService).createOrUpdate(entrepot);

        RequestBuilder requestBuilder = post("/entrepot/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.asJsonString(entrepot));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{nom:entrepot1,capacite:50.0,marchandisesStockees:[],occupation:0.0}";
        JSONAssert.assertEquals(result.getResponse().getContentAsString(), expected, false);
        assert(result.getResponse().getStatus() == HttpStatus.CREATED.value());
    }

    @Test
    public void putTest() throws Exception {
        Entrepot entrepot = new EntrepotEntity("entrepot1", 50);

        Mockito.when(entrepotService.getByNom(entrepot.getNom())).thenReturn(entrepot);
        Mockito.doNothing().when(entrepotService).createOrUpdate(entrepot);

        RequestBuilder requestBuilder = put("/entrepot/{nom}", entrepot.getNom())
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.asJsonString(entrepot));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{nom:entrepot1,capacite:50.0,marchandisesStockees:[],occupation:0.0}";
        JSONAssert.assertEquals(result.getResponse().getContentAsString(), expected, false);
        assert(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void deleteTest() throws Exception {
        Entrepot entrepot = new EntrepotEntity("entrepot1", 50);
        Mockito.when(entrepotService.getByNom(entrepot.getNom())).thenReturn(entrepot);
        Mockito.doNothing().when(entrepotService).remove(entrepot.getNom());

        RequestBuilder requestBuilder = delete("/entrepot/{nom}", entrepot.getNom())
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.asJsonString(entrepot));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assert(result.getResponse().getContentLength() == 0);
        assert(result.getResponse().getStatus() == HttpStatus.NO_CONTENT.value());
    }
}