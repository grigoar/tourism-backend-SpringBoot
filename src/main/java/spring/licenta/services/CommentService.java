package spring.licenta.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.licenta.dto.CommentDTO;
import spring.licenta.entities.City;
import spring.licenta.entities.Comment;
import spring.licenta.entities.Hotel;
import spring.licenta.entities.Restaurant;
import spring.licenta.entities.TouristAttraction;
import spring.licenta.entities.User;
import spring.licenta.errorhandler.EntityValidationException;
import spring.licenta.errorhandler.ResourceNotFoundException;
import spring.licenta.repositories.CommentRepository;
import spring.licenta.repositories.HotelRepository;
import spring.licenta.repositories.RestaurantRepository;
import spring.licenta.repositories.TouristAttractionRepository;
import spring.licenta.repositories.UserRepository;


@Service
@Transactional
public class CommentService {

		@Autowired
		private CommentRepository commentRepository;
		
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private HotelRepository hotelRepository;
		@Autowired
		private RestaurantRepository restaurantRepository;
		@Autowired
		private TouristAttractionRepository touristAttractionRepository;
		/*
		 * @Autowired private PasswordEncoder bcryptEncoder;
		 */
		private String commentUsername;
		
		public List<CommentDTO> findAll() {
			System.out.println("trece sa ?");
			List<Comment> comments = commentRepository.findAll();
			List<CommentDTO> toReturn = new ArrayList<CommentDTO>();
			System.out.println("trece sa faca o lista?");
			for (Comment comment : comments) {
				//String[] names = extractNames(user.getName());
				System.out.println("ajunge sa faca o lista?");
				CommentDTO dto = new CommentDTO.Builder()
						.id(comment.getId())
						.details(comment.getDetails())
						.date(comment.getDate())
						.restaurant_id(comment.getRestaurant_id())
						.hotel_id(comment.getHotel_id())
						.event_id(comment.getEvent_id())
						.touristAttraction_id(comment.getTouristAttraction_id())
						.user_id(comment.getUser_id())
						.commentusername(comment.getCommentUsername())
						.rating(comment.getRating())
						.create();
				toReturn.add(dto);
			}
			return toReturn;
		}
		
		public CommentDTO findCommentById(int commentId) {
			Comment comment = commentRepository.findById(commentId);
			if (comment == null) {
				throw new ResourceNotFoundException(City.class.getSimpleName());
			}
			//String[] names = extractNames(usr.getName());

			CommentDTO commentdto = new CommentDTO.Builder()
					.id(comment.getId())
					.details(comment.getDetails())
					.date(comment.getDate())
					.restaurant_id(comment.getRestaurant_id())
					.hotel_id(comment.getHotel_id())
					.event_id(comment.getEvent_id())
					.touristAttraction_id(comment.getTouristAttraction_id())
					.user_id(comment.getUser_id())
					.commentusername(comment.getCommentUsername())
					.rating(comment.getRating())
					.create();
			return commentdto;
		}
		
		public CommentRepository create(CommentDTO commentDTO) {
			System.out.println("Ajunge sa creeze un comment?");
			List<String> validationErrors = validateComment(commentDTO);
			if (!validationErrors.isEmpty()) {
				throw new EntityValidationException(Comment.class.getSimpleName(),validationErrors);
			}
			
			//User username= userRepository.findById(commentDTO.getUser_id());
			int idcomment=commentDTO.getUser_id();
			User username= userRepository.findById(idcomment);
			commentUsername=username.getUsername();
			System.out.println("am aflat username de la user" + commentUsername);
			Comment comment = new Comment();
			comment.setId(commentDTO.getId());
			comment.setDetails(commentDTO.getDetails());
			comment.setDate(new Date());
			comment.setRestaurant_id(commentDTO.getRestaurant_id());
			comment.setHotel_id(commentDTO.getHotel_id());
			comment.setEvent_id(commentDTO.getEvent_id());
			comment.setTouristAttraction_id(commentDTO.getTouristAttraction_id());
			comment.setUser_id(commentDTO.getUser_id());
			comment.setCommentUsername(commentUsername);
			comment.setRating(commentDTO.getRating());
			
			
			Comment commentR = commentRepository.save(comment);
			//am schimbat aici ce returnez
			//return comment.getId();
			return commentRepository;
		}
		
		public void updateComment(CommentDTO commentDTO) {
			System.out.println("Ajunge sa creeze un comment?");
			List<String> validationErrors = validateComment(commentDTO);
			if (!validationErrors.isEmpty()) {
				throw new EntityValidationException(Comment.class.getSimpleName(),validationErrors);
			}
			
			//User username= userRepository.findById(commentDTO.getUser_id());
			int idcomment=commentDTO.getUser_id();
			User username= userRepository.findById(idcomment);
			commentUsername=username.getUsername();
			System.out.println("am aflat username de la user" + commentUsername);
			Comment comment = new Comment();
			comment.setId(commentDTO.getId());
			comment.setDetails(commentDTO.getDetails());
			comment.setDate(new Date());
			comment.setRestaurant_id(commentDTO.getRestaurant_id());
			comment.setHotel_id(commentDTO.getHotel_id());
			comment.setEvent_id(commentDTO.getEvent_id());
			comment.setTouristAttraction_id(commentDTO.getTouristAttraction_id());
			comment.setUser_id(commentDTO.getUser_id());
			comment.setCommentUsername(commentUsername);
			comment.setRating(commentDTO.getRating());
			
			
			Comment commentR = commentRepository.save(comment);
			//am schimbat aici ce returnez
			//return comment.getId();
			//return commentRepository;
			
		}
		public void deleteCommentById(int id) {
			 System.out.println(id+" Incercam sa stergem commentul");
				commentRepository.deleteById(id);
				System.out.println(id+" asta ii ID-ul");
			    }
		
		private List<String> validateComment(CommentDTO comment) {
			List<String> validationErrors = new ArrayList<String>();

			if (comment.getDetails() == null || "".equals(comment.getDetails())) {
				validationErrors.add("Details field should not be empty");
			}

			/*if (city.getName() == null || "".equals(city.getName())) {
				validationErrors.add("Name field should not be empty");
			}

			if (city.getCountry() == null || "".equals(city.getCountry())) {
				validationErrors.add("Country does not have the correct format.");
			}
*/
			return validationErrors;
		}
		// cautam toate comments de la un hotel dupa id_ul hotelului 
		public List<CommentDTO> findAllHotelCommentsById(Integer hotel_id) {
			//System.out.println("Ce id"+cityId);
			Double ratingavr=0.0;
			Double ratingtotal=0.0;
			Double nr=0.0;
			System.out.println("Ce id"+hotel_id);
			List<Comment> comments = commentRepository.findAllHotelCommentsById(hotel_id);
		//	System.out.println("Ce id"+cityId);
			List<CommentDTO> toReturn = new ArrayList<CommentDTO>();
			for (Comment comment :comments) {
				if(comment.getRating()!=0) {
					ratingtotal+=comment.getRating();
					nr++;
				}
				//String comments = savings.getComment();
				CommentDTO dto = new CommentDTO.Builder()
						.id(comment.getId())
						.details(comment.getDetails())
						.date(comment.getDate())
						.restaurant_id(comment.getRestaurant_id())
						.hotel_id(comment.getHotel_id())
						.event_id(comment.getEvent_id())
						.touristAttraction_id(comment.getTouristAttraction_id())
						.user_id(comment.getUser_id())
						.commentusername(comment.getCommentUsername())
						.rating(comment.getRating())
						.create();
				toReturn.add(dto);
			}
			ratingavr=ratingtotal/nr;
			System.out.println("ratingavr al hotelului este "+ ratingavr);
			int id=hotel_id;
			Hotel ratinghotel=hotelRepository.findById(id);
			Hotel hotel = new Hotel();
			//city.setId(cityDTO.getId());
			hotel.setId(ratinghotel.getId());
			hotel.setName(ratinghotel.getName());
			hotel.setAddress(ratinghotel.getAddress());
			hotel.setDetails(ratinghotel.getDetails());
			hotel.setContact(ratinghotel.getContact());
			hotel.setWebsite(ratinghotel.getWebsite());
			hotel.setCityId(ratinghotel.getCityId());
			if(ratingavr.isNaN())hotel.setRatingavr(0.0);
				else hotel.setRatingavr(ratingavr);
			hotel.setLat(ratinghotel.getLat());
			hotel.setLon(ratinghotel.getLon());
			hotel.setRooms(ratinghotel.getRooms());
			
			Hotel hotelR = hotelRepository.save(hotel);
			
			System.out.println("Ce id"+hotel_id);
			System.out.println("Am terminat de cautat commenturile pentru un hotel dintr-un oras");
			return toReturn;
		}
		
		// cautam toate comments de la un restaurant dupa id_ul hotelului 
				public List<CommentDTO> findAllRestaurantCommentsById(Integer restaurant_id) {
					//System.out.println("Ce id"+cityId);
					Double ratingavr=0.0;
					Double ratingtotal=0.0;
					Double nr=0.0;
					System.out.println("Ce id"+restaurant_id);
					List<Comment> comments = commentRepository.findAllRestaurantCommentsById(restaurant_id);
				//	System.out.println("Ce id"+cityId);
					List<CommentDTO> toReturn = new ArrayList<CommentDTO>();
					for (Comment comment :comments) {
						if(comment.getRating()!=0) {
							ratingtotal+=comment.getRating();
							nr++;
						}
						//String comments = savings.getComment();
						CommentDTO dto = new CommentDTO.Builder()
								.id(comment.getId())
								.details(comment.getDetails())
								.date(comment.getDate())
								.restaurant_id(comment.getRestaurant_id())
								.hotel_id(comment.getHotel_id())
								.event_id(comment.getEvent_id())
								.touristAttraction_id(comment.getTouristAttraction_id())
								.user_id(comment.getUser_id())
								.commentusername(comment.getCommentUsername())
								.rating(comment.getRating())
								.create();
						toReturn.add(dto);
					}
					ratingavr=ratingtotal/nr;
					System.out.println("ratingavr al restaurantului este "+ ratingavr);
					int id=restaurant_id;
					Restaurant ratingrestaurant=restaurantRepository.findById(id);
					Restaurant restaurant = new Restaurant();
					//city.setId(cityDTO.getId());
					restaurant.setId(ratingrestaurant.getId());
					restaurant.setName(ratingrestaurant.getName());
					restaurant.setAddress(ratingrestaurant.getAddress());
					restaurant.setDetails(ratingrestaurant.getDetails());
					restaurant.setContact(ratingrestaurant.getContact());
					restaurant.setWebsite(ratingrestaurant.getWebsite());
					restaurant.setCityId(ratingrestaurant.getCityId());
					
					if(ratingavr.isNaN())restaurant.setRatingavr(0.0);
					else restaurant.setRatingavr(ratingavr);
					restaurant.setLat(ratingrestaurant.getLat());
					restaurant.setLon(ratingrestaurant.getLon());
					restaurant.setTables(ratingrestaurant.getTables());
					
					Restaurant restaurantR = restaurantRepository.save(restaurant);
					
					System.out.println("Ce id"+restaurant_id);
					System.out.println("Am terminat de cautat commenturile pentru un restaurant dintr-un oras");
					return toReturn;
				}
			
		public List<CommentDTO> findAllEventCommentsById(Integer event_id) {
			//System.out.println("Ce id"+cityId);
			
			System.out.println("Ce id"+event_id);
			List<Comment> comments = commentRepository.findAllEventCommentsById(event_id);
		//	System.out.println("Ce id"+cityId);
			List<CommentDTO> toReturn = new ArrayList<CommentDTO>();
			for (Comment comment :comments) {
				
				//String comments = savings.getComment();
				CommentDTO dto = new CommentDTO.Builder()
						.id(comment.getId())
						.details(comment.getDetails())
						.date(comment.getDate())
						.restaurant_id(comment.getRestaurant_id())
						.hotel_id(comment.getHotel_id())
						.event_id(comment.getEvent_id())
						.touristAttraction_id(comment.getTouristAttraction_id())
						.user_id(comment.getUser_id())
						.commentusername(comment.getCommentUsername())
						.rating(comment.getRating())
						.create();
				toReturn.add(dto);
			}
			
			
			System.out.println("Ce id"+event_id);
			System.out.println("Am terminat de cautat commenturile pentru un event dintr-un oras");
			return toReturn;
		}
		
		// cautam toate comments de la un touristAttraction dupa id_ul hotelului 
		public List<CommentDTO> findAllTouristAttractionCommentsById(Integer touristAttraction_id) {
			//System.out.println("Ce id"+cityId);
			Double ratingavr=0.0;
			Double ratingtotal=0.0;
			Double nr=0.0;
			System.out.println("Ce id"+touristAttraction_id);
			List<Comment> comments = commentRepository.findAllTouristAttractionCommentsById(touristAttraction_id);
		//	System.out.println("Ce id"+cityId);
			List<CommentDTO> toReturn = new ArrayList<CommentDTO>();
			for (Comment comment :comments) {
				if(comment.getRating()!=0) {
					ratingtotal+=comment.getRating();
					nr++;
				}
				//String comments = savings.getComment();
				CommentDTO dto = new CommentDTO.Builder()
						.id(comment.getId())
						.details(comment.getDetails())
						.date(comment.getDate())
						.restaurant_id(comment.getRestaurant_id())
						.hotel_id(comment.getHotel_id())
						.event_id(comment.getEvent_id())
						.touristAttraction_id(comment.getTouristAttraction_id())
						.user_id(comment.getUser_id())
						.commentusername(comment.getCommentUsername())
						.rating(comment.getRating())
						.create();
				toReturn.add(dto);
			}
			ratingavr=ratingtotal/nr;
			System.out.println("ratingavr al touristAttractionului este "+ ratingavr);
			int id=touristAttraction_id;
			TouristAttraction ratingtouristAttraction=touristAttractionRepository.findById(id);
			TouristAttraction touristAttraction = new TouristAttraction();
			//city.setId(cityDTO.getId());
			touristAttraction.setId(ratingtouristAttraction.getId());
			touristAttraction.setName(ratingtouristAttraction.getName());
			touristAttraction.setAddress(ratingtouristAttraction.getAddress());
			touristAttraction.setDetails(ratingtouristAttraction.getDetails());
			touristAttraction.setCityId(ratingtouristAttraction.getCityId());
			
			if(ratingavr.isNaN())touristAttraction.setRatingavr(0.0);
			else touristAttraction.setRatingavr(ratingavr);
			touristAttraction.setLat(ratingtouristAttraction.getLat());
			touristAttraction.setLon(ratingtouristAttraction.getLon());
			
			TouristAttraction touristAttractionR = touristAttractionRepository.save(touristAttraction);
			
			System.out.println("Ce id"+touristAttraction_id);
			System.out.println("Am terminat de cautat commenturile pentru un touristAttraction dintr-un oras");
			return toReturn;
		}
		
		public List<Double> getCommentsByRatingHotel(Integer hotel_id){
			List<Double> ratings= new ArrayList<Double>(12);
			List<CommentDTO> comments=findAllHotelCommentsById(hotel_id);
			
			double avr1=0.0,avr2=0.0,avr3=0.0,avr4=0.0,avr5=0.0,avr6=0.0,avr7=0.0,avr8=0.0,avr9=0.0,avr10=0.0,avr11=0.0,avr12=0.0;
			int nr1=0,nr2=0,nr3=0,nr4=0,nr5=0,nr6=0,nr7=0,nr8=0,nr9=0,nr10=0,nr11=0,nr12=0;
			
			for(CommentDTO comment:comments) {
				if(comment.getRating()!=0) {
					//ratings.add(comment.getRating().doubleValue());
					Date dateComment=comment.getDate();
					LocalDate date=dateComment.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int month=date.getMonthValue();
					switch(month) {
					case 1:
						System.out.println("Luna 1 "+comment.getId()+" si are date: "+comment.getDate());
						nr1++;
						avr1+=comment.getRating();
						break;
					case 2:
						System.out.println("Luna 2 "+comment.getId()+" si are date: "+comment.getDate());
						nr2++;
						avr2+=comment.getRating();
						break;
					case 3:
						System.out.println("Luna 3 "+comment.getId()+" si are date: "+comment.getDate());
						nr3++;
						avr3+=comment.getRating();
						break;
					case 4:
						System.out.println("Luna 4 "+comment.getId()+" si are date: "+comment.getDate());
						nr4++;
						avr4+=comment.getRating();
						break;
					case 5:
						System.out.println("Luna 5 "+comment.getId()+" si are date: "+comment.getDate());
						nr5++;
						avr5+=comment.getRating();
						break;
					case 6:
						System.out.println("Luna 6 "+comment.getId()+" si are date: "+comment.getDate());
						nr6++;
						avr6+=comment.getRating();
						break;
					case 7:
						System.out.println("Luna 7 "+comment.getId()+" si are date: "+comment.getDate());
						nr7++;
						avr7+=comment.getRating();
						break;
					case 8:
						System.out.println("Luna 8 "+comment.getId()+" si are date: "+comment.getDate());
						nr8++;
						avr8+=comment.getRating();
						break;
					case 9:
						System.out.println("Luna 9 "+comment.getId()+" si are date: "+comment.getDate());
						nr9++;
						avr9+=comment.getRating();
						break;
					case 10:
						System.out.println("Luna 10 "+comment.getId()+" si are date: "+comment.getDate());
						nr10++;
						avr10+=comment.getRating();
						break;
					case 11:
						System.out.println("Luna 11 "+comment.getId()+" si are date: "+comment.getDate());
						nr11++;
						avr11+=comment.getRating();
						break;
					case 12:
						System.out.println("Luna 12 "+comment.getId()+" si are date: "+comment.getDate());
						nr12++;
						avr12+=comment.getRating();
						break;
					default: System.out.println("no valid data");
					}
					
					
					
				}
				
			}
			if(nr1!=0) {avr1=avr1/nr1;}
			else avr1=0.0;
			if(nr2!=0) {avr2=avr2/nr2;}
			else avr2=0.0;
			if(nr3!=0) {avr3=avr3/nr3;}
			else avr3=0.0;
			if(nr4!=0) {avr4=avr4/nr4;}
			else avr4=0.0;
			if(nr5!=0) {avr5=avr5/nr5;}
			else avr5=0.0;
			if(nr6!=0) {avr6=avr6/nr6;}
			else avr6=0.0;
			if(nr7!=0) {avr7=avr7/nr7;}
			else avr7=0.0;
			if(nr8!=0) {avr8=avr8/nr8;}
			else avr8=0.0;
			if(nr9!=0) {avr9=avr9/nr9;}
			else avr9=0.0;
			if(nr10!=0) {avr10=avr10/nr10;}
			else avr10=0.0;
			if(nr11!=0) {avr11=avr11/nr11;}
			else avr11=0.0;
			if(nr12!=0) {avr12=avr12/nr12;}
			else avr12=0.0;
			
			ratings.add(((double)((int)(avr1 *100.0)))/100.0);
			ratings.add(((double)((int)(avr2 *100.0)))/100.0);
			ratings.add(((double)((int)(avr3 *100.0)))/100.0);
			ratings.add(((double)((int)(avr4 *100.0)))/100.0);
			ratings.add(((double)((int)(avr5 *100.0)))/100.0);
			ratings.add(((double)((int)(avr6 *100.0)))/100.0);
			ratings.add(((double)((int)(avr7 *100.0)))/100.0);
			ratings.add(((double)((int)(avr8 *100.0)))/100.0);
			ratings.add(((double)((int)(avr9 *100.0)))/100.0);
			ratings.add(((double)((int)(avr10 *100.0)))/100.0);
			ratings.add(((double)((int)(avr11 *100.0)))/100.0);
			ratings.add(((double)((int)(avr12 *100.0)))/100.0);
			
		    System.out.println(Arrays.toString(ratings.toArray()));
			return ratings;
		}
		
		//private static DecimalFormat decimalFormatter = new DecimalFormat("#.##");
		public List<Double> getCommentsByRatingRestaurant(Integer restaurant_id){
			//decimalFormatter.setRoundingMode(RoundingMode.UP);
			List<Double> ratings= new ArrayList<Double>(12);
			List<CommentDTO> comments=findAllRestaurantCommentsById(restaurant_id);
			
			double avr1=0.0,avr2=0.0,avr3=0.0,avr4=0.0,avr5=0.0,avr6=0.0,avr7=0.0,avr8=0.0,avr9=0.0,avr10=0.0,avr11=0.0,avr12=0.0;
			int nr1=0,nr2=0,nr3=0,nr4=0,nr5=0,nr6=0,nr7=0,nr8=0,nr9=0,nr10=0,nr11=0,nr12=0;
			
			for(CommentDTO comment:comments) {
				if(comment.getRating()!=0) {
					//ratings.add(comment.getRating().doubleValue());
					Date dateComment=comment.getDate();
					LocalDate date=dateComment.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int month=date.getMonthValue();
					switch(month) {
					case 1:
						System.out.println("Luna 1 "+comment.getId()+" si are date: "+comment.getDate());
						nr1++;
						avr1+=comment.getRating();
						break;
					case 2:
						System.out.println("Luna 2 "+comment.getId()+" si are date: "+comment.getDate());
						nr2++;
						avr2+=comment.getRating();
						break;
					case 3:
						System.out.println("Luna 3 "+comment.getId()+" si are date: "+comment.getDate());
						nr3++;
						avr3+=comment.getRating();
						break;
					case 4:
						System.out.println("Luna 4 "+comment.getId()+" si are date: "+comment.getDate());
						nr4++;
						avr4+=comment.getRating();
						break;
					case 5:
						System.out.println("Luna 5 "+comment.getId()+" si are date: "+comment.getDate());
						nr5++;
						avr5+=comment.getRating();
						break;
					case 6:
						System.out.println("Luna 6 "+comment.getId()+" si are date: "+comment.getDate());
						nr6++;
						avr6+=comment.getRating();
						break;
					case 7:
						System.out.println("Luna 7 "+comment.getId()+" si are date: "+comment.getDate());
						nr7++;
						avr7+=comment.getRating();
						break;
					case 8:
						System.out.println("Luna 8 "+comment.getId()+" si are date: "+comment.getDate());
						nr8++;
						avr8+=comment.getRating();
						break;
					case 9:
						System.out.println("Luna 9 "+comment.getId()+" si are date: "+comment.getDate());
						nr9++;
						avr9+=comment.getRating();
						break;
					case 10:
						System.out.println("Luna 10 "+comment.getId()+" si are date: "+comment.getDate());
						nr10++;
						avr10+=comment.getRating();
						break;
					case 11:
						System.out.println("Luna 11 "+comment.getId()+" si are date: "+comment.getDate());
						nr11++;
						avr11+=comment.getRating();
						break;
					case 12:
						System.out.println("Luna 12 "+comment.getId()+" si are date: "+comment.getDate());
						nr12++;
						avr12+=comment.getRating();
						break;
					default: System.out.println("no valid data");
					}
					
					
					
				}
				
			}
			if(nr1!=0) {avr1=avr1/nr1;}
			else avr1=0.0;
			if(nr2!=0) {avr2=avr2/nr2;}
			else avr2=0.0;
			if(nr3!=0) {avr3=avr3/nr3;}
			else avr3=0.0;
			if(nr4!=0) {avr4=avr4/nr4;}
			else avr4=0.0;
			if(nr5!=0) {avr5=avr5/nr5;}
			else avr5=0.0;
			if(nr6!=0) {avr6=avr6/nr6;}
			else avr6=0.0;
			if(nr7!=0) {avr7=avr7/nr7;}
			else avr7=0.0;
			if(nr8!=0) {avr8=avr8/nr8;}
			else avr8=0.0;
			if(nr9!=0) {avr9=avr9/nr9;}
			else avr9=0.0;
			if(nr10!=0) {avr10=avr10/nr10;}
			else avr10=0.0;
			if(nr11!=0) {avr11=avr11/nr11;}
			else avr11=0.0;
			if(nr12!=0) {avr12=avr12/nr12;}
			else avr12=0.0;
			
			
			//avr1=Math.floor(avr1 * 100) / 100;
			//System.out.println("2 decimals only"+avr1);
			//avr12 = ((double)((int)(avr12 *100.0)))/100.0;
			//BigDecimal.valueOf(avr4).setScale(2, BigDecimal.ROUND_HALF_UP);
			ratings.add(((double)((int)(avr1 *100.0)))/100.0);
			ratings.add(((double)((int)(avr2 *100.0)))/100.0);
			ratings.add(((double)((int)(avr3 *100.0)))/100.0);
			ratings.add(((double)((int)(avr4 *100.0)))/100.0);
			ratings.add(((double)((int)(avr5 *100.0)))/100.0);
			ratings.add(((double)((int)(avr6 *100.0)))/100.0);
			ratings.add(((double)((int)(avr7 *100.0)))/100.0);
			ratings.add(((double)((int)(avr8 *100.0)))/100.0);
			ratings.add(((double)((int)(avr9 *100.0)))/100.0);
			ratings.add(((double)((int)(avr10 *100.0)))/100.0);
			ratings.add(((double)((int)(avr11 *100.0)))/100.0);
			ratings.add(((double)((int)(avr12 *100.0)))/100.0);
			
		    System.out.println(Arrays.toString(ratings.toArray()));
			return ratings;
		}
		
		public List<Double> getCommentsByRatingTouristAttraction(Integer touristAttraction_id){
			//decimalFormatter.setRoundingMode(RoundingMode.UP);
			List<Double> ratings= new ArrayList<Double>(12);
			List<CommentDTO> comments=findAllTouristAttractionCommentsById(touristAttraction_id);
			
			double avr1=0.0,avr2=0.0,avr3=0.0,avr4=0.0,avr5=0.0,avr6=0.0,avr7=0.0,avr8=0.0,avr9=0.0,avr10=0.0,avr11=0.0,avr12=0.0;
			int nr1=0,nr2=0,nr3=0,nr4=0,nr5=0,nr6=0,nr7=0,nr8=0,nr9=0,nr10=0,nr11=0,nr12=0;
			
			for(CommentDTO comment:comments) {
				if(comment.getRating()!=0) {
					//ratings.add(comment.getRating().doubleValue());
					Date dateComment=comment.getDate();
					LocalDate date=dateComment.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int month=date.getMonthValue();
					switch(month) {
					case 1:
						System.out.println("Luna 1 "+comment.getId()+" si are date: "+comment.getDate());
						nr1++;
						avr1+=comment.getRating();
						break;
					case 2:
						System.out.println("Luna 2 "+comment.getId()+" si are date: "+comment.getDate());
						nr2++;
						avr2+=comment.getRating();
						break;
					case 3:
						System.out.println("Luna 3 "+comment.getId()+" si are date: "+comment.getDate());
						nr3++;
						avr3+=comment.getRating();
						break;
					case 4:
						System.out.println("Luna 4 "+comment.getId()+" si are date: "+comment.getDate());
						nr4++;
						avr4+=comment.getRating();
						break;
					case 5:
						System.out.println("Luna 5 "+comment.getId()+" si are date: "+comment.getDate());
						nr5++;
						avr5+=comment.getRating();
						break;
					case 6:
						System.out.println("Luna 6 "+comment.getId()+" si are date: "+comment.getDate());
						nr6++;
						avr6+=comment.getRating();
						break;
					case 7:
						System.out.println("Luna 7 "+comment.getId()+" si are date: "+comment.getDate());
						nr7++;
						avr7+=comment.getRating();
						break;
					case 8:
						System.out.println("Luna 8 "+comment.getId()+" si are date: "+comment.getDate());
						nr8++;
						avr8+=comment.getRating();
						break;
					case 9:
						System.out.println("Luna 9 "+comment.getId()+" si are date: "+comment.getDate());
						nr9++;
						avr9+=comment.getRating();
						break;
					case 10:
						System.out.println("Luna 10 "+comment.getId()+" si are date: "+comment.getDate());
						nr10++;
						avr10+=comment.getRating();
						break;
					case 11:
						System.out.println("Luna 11 "+comment.getId()+" si are date: "+comment.getDate());
						nr11++;
						avr11+=comment.getRating();
						break;
					case 12:
						System.out.println("Luna 12 "+comment.getId()+" si are date: "+comment.getDate());
						nr12++;
						avr12+=comment.getRating();
						break;
					default: System.out.println("no valid data");
					}
					
					
					
				}
				
			}
			if(nr1!=0) {avr1=avr1/nr1;}
			else avr1=0.0;
			if(nr2!=0) {avr2=avr2/nr2;}
			else avr2=0.0;
			if(nr3!=0) {avr3=avr3/nr3;}
			else avr3=0.0;
			if(nr4!=0) {avr4=avr4/nr4;}
			else avr4=0.0;
			if(nr5!=0) {avr5=avr5/nr5;}
			else avr5=0.0;
			if(nr6!=0) {avr6=avr6/nr6;}
			else avr6=0.0;
			if(nr7!=0) {avr6=avr7/nr7;}
			else avr7=0.0;
			if(nr8!=0) {avr8=avr8/nr8;}
			else avr8=0.0;
			if(nr9!=0) {avr9=avr9/nr9;}
			else avr9=0.0;
			if(nr10!=0) {avr10=avr10/nr10;}
			else avr10=0.0;
			if(nr11!=0) {avr11=avr11/nr11;}
			else avr11=0.0;
			if(nr12!=0) {avr12=avr12/nr12;}
			else avr12=0.0;
			
			
			//avr1=Math.floor(avr1 * 100) / 100;
			//System.out.println("2 decimals only"+avr1);
			//avr12 = ((double)((int)(avr12 *100.0)))/100.0;
			//BigDecimal.valueOf(avr4).setScale(2, BigDecimal.ROUND_HALF_UP);
			ratings.add(((double)((int)(avr1 *100.0)))/100.0);
			ratings.add(((double)((int)(avr2 *100.0)))/100.0);
			ratings.add(((double)((int)(avr3 *100.0)))/100.0);
			ratings.add(((double)((int)(avr4 *100.0)))/100.0);
			ratings.add(((double)((int)(avr5 *100.0)))/100.0);
			ratings.add(((double)((int)(avr6 *100.0)))/100.0);
			ratings.add(((double)((int)(avr7 *100.0)))/100.0);
			ratings.add(((double)((int)(avr8 *100.0)))/100.0);
			ratings.add(((double)((int)(avr9 *100.0)))/100.0);
			ratings.add(((double)((int)(avr10 *100.0)))/100.0);
			ratings.add(((double)((int)(avr11 *100.0)))/100.0);
			ratings.add(((double)((int)(avr12 *100.0)))/100.0);
			
		    System.out.println(Arrays.toString(ratings.toArray()));
			return ratings;
		}
		
		
	
	}




