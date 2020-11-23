package spring.licenta.tests.services;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import spring.licenta.dto.CityDTO;
import spring.licenta.entities.City;
import spring.licenta.repositories.CityRepository;
import spring.licenta.services.CityService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CityServiceTests {

	private static CityDTO c1;
	private static CityDTO c2;

	private static CityDTO c3Result;

	private static City city1;
	private static City city2;

	@Mock
	private static City city;

	@Mock
	private CityRepository cityRepository;

	@InjectMocks
	private CityService cityService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	public static void init() {
		city1 = new City(7, "NewYork", "SUA","sua description", 45.33, 44.22);
		city2 = new City(8, "Los Angeles", "SUA","sua description", 48.33, 42.22);
		City city3 = new City(9, "San Francisco", "SUA","sua description", 41.33, 49.22);
		c1 = new CityDTO.Builder().id(7).name("New York").country("SUA").lat(44.22).lon(41.11).create();
		c2 = new CityDTO.Builder().id(8).name("Los Angeles").country("SUA").lat(49.22).lon(40.11).create();
		city = new City(7, "NewYork", "SUA","sua description", 45.33, 44.22);

	}

	
	@Test
	public void findAllTest_WhenRecord() {

		Mockito.when(cityRepository.findAll()).thenReturn(Arrays.asList(city1, city2));
		assertThat(cityRepository.findAll().size(), is(2));
		assertThat(cityRepository.findAll().get(0), is(city1));
		assertThat(cityRepository.findAll().get(1), is(city2));
		Mockito.verify(cityRepository, Mockito.times(3)).findAll();
	}

	@Test
	public void findAllTest_WhenNoRecord() {

		Mockito.when(cityRepository.findAll()).thenReturn(Arrays.asList());
		assertThat(cityRepository.findAll().size(), is(0));
		Mockito.verify(cityRepository, Mockito.times(1)).findAll();

	}


	@Test
	void updateTest() {
		/*
		 * c1 = new CityDTO.Builder() .id(7) .name("New York") .country("SUA")
		 * .lat(44.22) .lon(41.11) .create();
		 */
		Mockito.when(cityRepository.save(any(City.class))).thenReturn(any(City.class));
		cityService.updateCity(c1);
		Mockito.verify(cityRepository, Mockito.times(1)).save(any(City.class));
	}

	void createTest() {
		/*
		 * c1 = new CityDTO.Builder() .id(7) .name("New York") .country("SUA")
		 * .lat(44.22) .lon(41.11) .create();
		 */
		Mockito.when(cityRepository.save(any(City.class))).thenReturn(any(City.class));
		cityService.create(c1);
		Mockito.verify(cityRepository, Mockito.times(1)).save(any(City.class));
	}

	@Test
	public void findById() {
//city1=new City(7,"NewYork", "SUA", 45.33, 44.22); 
		Mockito.when(cityRepository.findById(7)).thenReturn(city1);

		CityDTO cityR = cityService.findCityById(7);
		assertEquals("NewYork", cityR.getName());
		assertEquals("SUA", cityR.getCountry());
		assertEquals(45.33, cityR.getLat().doubleValue());
		assertEquals(44.22, cityR.getLon().doubleValue());
	}

	@Test
	void deleteById() {
		cityService.deleteCityById(1);
		Mockito.verify(cityRepository, Mockito.times(1)).deleteById(1);
	}

//    @Test
//    void createOrUpdate() {
//
//        Mockito.when(productRepository.save(p1)).thenReturn(p1);
//        assertThat(productService.createOrUpdate(p1), is(p1));
//        Mockito.verify(productRepository, Mockito.times(1)).save(p1);
//
//        Mockito.when(productRepository.save(p2)).thenReturn(p2);
//        assertThat(productService.createOrUpdate(p2).getName(), is("P2"));
//        Mockito.verify(productRepository, Mockito.times(1)).save(p2);
//    }
//
//    @Test
//    void deleteById() {
//        productService.deleteById(1L);
//        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1L);
//    }

	/*
	 * @Test void shouldSavedCitySuccessFully() { final City city = new City(1,
	 * "San Francisco", "SUA", 41.33, 49.22);
	 * given(cityRepository.findById(city.getId())).willReturn(Optional.empty());
	 * given(cityRepository.save(city)).willAnswer(invocation ->
	 * invocation.getArgument(0)); CityDTO newCity = new
	 * CityDTO.Builder().id(city.getId()).name(city.getName()).country(city.
	 * getCountry()) .lat(city.getLat()).lon(city.getLon()).create(); City savedCity
	 * = cityService.create(newCity); assertEquals(1, savedCity.getId().intValue());
	 * verify(cityRepository).save(any(City.class)); }
	 */
	
}
