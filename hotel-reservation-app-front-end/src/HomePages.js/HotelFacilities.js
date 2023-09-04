import React from 'react';
import './HotelFacilities.css';


const facilities = [
  {
    name: 'Swimming Pool',
    imageUrl: 'https://d1e00ek4ebabms.cloudfront.net/production/fc70615b-fafa-4c42-afc7-9dcb6ed4b1ab.jpg',
  },
  {
    name: 'Gym',
    imageUrl: 'https://www.omnihotels.com/-/media/images/hotels/loudtn/hotel/loudtn-omni-louisville-fitness-center.jpg?h=660&la=en&w=1170',
  },
  {
    name: 'Dining',
    imageUrl: 'https://www.jetsetter.com//uploads/sites/7/2018/07/2zFs3Mzm.jpeg',
  },
  {
    name: 'Parking',
    imageUrl: 'https://images.summitmedia-digital.com/topgear/images/2022/03/03/parking-tips-main-1646277296.jpg',
  },
  {
    name: 'Gaming',
    imageUrl: 'https://surfairbeachhotel.com.au/wp-content/uploads/2021/06/GamingRoomSurfair-1290x650.jpeg',
  },
  {
    name: 'Musical Instruments',
    imageUrl: 'https://png.pngtree.com/background/20230425/original/pngtree-piano-is-sitting-in-a-room-picture-image_2467345.jpg',
  },
 
];

const HotelFacilities = () => {
  return (
    <>
        <div className='facilities-text'>
            <h3>Our Hotels Included Facilities</h3>
        </div>
    <div className="facilities-container">
      {facilities.map((facility, index) => (
          <div className="facility-item" key={index}>
          <img src={facility.imageUrl} alt={facility.name} />
          <div className="facility-overlay">
            <p className="facility-name">{facility.name}</p>
          </div>
        </div>
      ))}
    </div>
    </>
      
  );
};

export default HotelFacilities;
