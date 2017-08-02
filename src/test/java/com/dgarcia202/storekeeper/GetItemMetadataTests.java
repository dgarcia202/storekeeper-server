package com.dgarcia202.storekeeper;

import com.dgarcia202.storekeeper.entity.ItemMetadataEntity;
import com.dgarcia202.storekeeper.repository.ItemMetadataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GetItemMetadataTests
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemMetadataRepository itemMetadataRepositoryMock;

    @Test
    public void canGetMetadataTest() throws Exception
    {
        UUID itemId = UUID.randomUUID();
        when(this.itemMetadataRepositoryMock.findOne(itemId))
                .thenReturn(new ItemMetadataEntity());

        this.mockMvc.perform(get("/api/items/" + itemId + "/metadata"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void nonExistentItemReturnsNotFoundTest() throws Exception
    {
        UUID itemId = UUID.randomUUID();
        when(this.itemMetadataRepositoryMock.findOne(itemId))
                .thenReturn(null);

        this.mockMvc.perform(get("/api/items/" + itemId + "/metadata"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void databaseErrorReturnsInternalServerErrorTest() throws Exception
    {
        UUID itemId = UUID.randomUUID();
        when(this.itemMetadataRepositoryMock.findOne(itemId))
                .thenThrow(new IllegalArgumentException());

        this.mockMvc.perform(get("/api/items/" + itemId + "/metadata"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}
