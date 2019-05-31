package de.predikant.conference.service.test;

import de.predikant.conference.service.TestDto;
import de.predikant.conference.service.TestEntity;
import org.junit.Assert;
import org.junit.Test;

public class JaCoCoTest {

    @Test
    public void testJaCoCo() {
        TestDto testDto = new TestDto();
        TestEntity testEntity = new TestEntity();

        System.out.println("TestDto:" + TestDto.class.getDeclaredFields().length);
        System.out.println("TestEntity:" + TestEntity.class.getDeclaredFields().length);

        Assert.assertEquals(TestDto.class.getDeclaredFields().length, TestEntity.class.getDeclaredFields().length);
    }
}
