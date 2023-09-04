import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './HotelAmenities.css'; // Add your custom styling here

const amenitiesData = [
  { id: 1, name: 'Swimming Pool', logo: 'https://e7.pngegg.com/pngimages/1000/706/png-clipart-swimming-pools-hot-tub-room-infinity-pool-hotel-swimming-pool-symbol-blue-text.png', image: 'https://d1e00ek4ebabms.cloudfront.net/production/fc70615b-fafa-4c42-afc7-9dcb6ed4b1ab.jpg' },
  { id: 2, name: 'Gym', logo: 'https://png.pngtree.com/png-vector/20220520/ourmid/pngtree-gym-facility-for-the-customers-at-five-star-hotels-png-image_4704890.png', image: 'https://www.omnihotels.com/-/media/images/hotels/loudtn/hotel/loudtn-omni-louisville-fitness-center.jpg?h=660&la=en&w=1170' },
  { id: 3, name: 'Parking', logo: 'https://img.freepik.com/free-vector/parking_24908-54068.jpg?w=2000', image: 'https://images.summitmedia-digital.com/topgear/images/2022/03/03/parking-tips-main-1646277296.jpg' },
  { id: 4, name: 'Gaming', logo: 'https://e7.pngegg.com/pngimages/199/143/png-clipart-black-controller-art-emoji-video-game-sms-game-game-multimedia-messaging-service.png', image: 'https://onecms-res.cloudinary.com/image/upload/s--pivFyn0G--/f_auto,q_auto/c_fill,g_auto,h_622,w_830/v1/mediacorp/tdy/image/2022/05/18/20220518_handout_razer_gaming_room_fairmont.jpg?itok=5ISjOVjw' },
  { id: 5, name: 'Dining', logo: 'https://banner2.cleanpng.com/20180515/jje/kisspng-restaurant-food-industry-dolphin-square-kitchen-co-5afabe27665656.6911052815263821194192.jpg', image: 'https://www.jetsetter.com//uploads/sites/7/2018/07/2zFs3Mzm.jpeg' },
  { id: 6, name: 'Music', logo: 'https://e7.pngegg.com/pngimages/69/982/png-clipart-music-icon-itunes-computer-icons-logo-itunes-text-logo.png', image: 'https://png.pngtree.com/background/20230425/original/pngtree-piano-is-sitting-in-a-room-picture-image_2467345.jpg' },
  
];

function HotelAmenities() {
  const [selectedAmenity, setSelectedAmenity] = useState(null);

  const handleAmenityClick = (amenityId) => {
    setSelectedAmenity(amenityId);
  };

  return (
    <div className="hotel-amenities-container">
      <div className="main-heading">
        <h2 className="hotel-amenities-heading">The Facilities available in Hotels</h2>
      </div>
      <div className="logos-container">
        <div className="amenities-grid">
          {amenitiesData.map((amenity) => (
            <div
              key={amenity.id}
              className={`amenity-item ${selectedAmenity === amenity.id ? 'selected' : ''}`}
              onClick={() => handleAmenityClick(amenity.id)}
            >
              <img src={amenity.logo} alt={amenity.name} />
              <p className="amenity-name">{amenity.name}</p>
            </div>
          ))}
        </div>
        <div className="amenity-details">
          {selectedAmenity !== null && (
            <img
              src={amenitiesData.find((amenity) => amenity.id === selectedAmenity).image}
              alt={amenitiesData.find((amenity) => amenity.id === selectedAmenity).name}
            />
          )}
        </div>
      </div>
    </div>
  );
}

export default HotelAmenities;
