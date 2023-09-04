


import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import './LandingPage.css'
import axios from 'axios';

function LandingPage() {
  const [cities, setCities] = useState([]);
  const [selectedCity, setSelectedCity] = useState('');
  const [checkInDate, setCheckInDate] = useState(new Date());
  const [checkOutDate, setCheckOutDate] = useState(new Date());
  const [hotelType, setHotelType] = useState('');
  
  const [searchClicked, setSearchClicked] = useState(false);

  const history = useHistory();

  useEffect(() => {
    // Fetch city data from the backend
    axios.get('http://localhost:8080/hotel/get-cities')
      .then(response => setCities(response.data))
      .catch(error => console.error('Error fetching cities:', error));
  }, []);

  const handleDateChange = (date, dateType) => {
    if (dateType === 'checkIn') {
      setCheckInDate(date);
      // Ensure checkOutDate is after checkInDate
      if (date > checkOutDate) {
        setCheckOutDate(date);
      }
    } else if (dateType === 'checkOut') {
      setCheckOutDate(date);
    }
  };

  const handleSearch = () => {
    setSearchClicked(true);
    // Navigate to the Hotels page with query parameters
    history.push(`/filter-hotel/${selectedCity}/${hotelType}`);
  };
  console.log(cities);
  console.log()
  return (
    <>
      <div id="carouselExampleInterval" className="carousel slide" data-bs-ride="carousel">
        <div className="carousel-inner">
          <div className="carousel-item active" data-bs-interval="3000">
            <img src="https://assets.onthebeach.co.uk/m/6f74b81930b9756b/Large-hotelimages-stella-island-luxury-resort-spa-adults-only-3128622-3" className="d-block w-100" alt="carousel" />
            <div className="carousel-text">Welcome to the Exquisite.<br />
              World of Plan My Stay.<br />
            </div>
            <div className='carousel-subtext'><br />Experience Excellence and Unmatched Comfort</div>
          </div>
          <div className="carousel-item " data-bs-interval="3000">
            <img src="https://thelasthotelstl.com/wp-content/uploads/2021/11/The-Last-Hotel-Rooftop-Pool-Bar-scaled2.jpg" className="d-block w-100" alt="Carousel" />
            <div className="carousel-text"><br /><br />Your Journey Begins Here
            </div>
            <div className='carousel-subtext' ><br /><br /><br />Discover a User-Friendly Platform for Your Convenience</div>
          </div>
          <div className="carousel-item" data-bs-interval="2000">
            <img src="https://media.istockphoto.com/id/109350324/photo/modern-lounge-chairs-next-to-swimming-pool.jpg?s=612x612&w=0&k=20&c=WRUYHH2Vhn1sZuAh2duRgm7538mdaY7f-QVYG2616kA=" className="d-block w-100" alt="Image 2" />
            <div className="carousel-text">Unveil Extraordinary Moments <br />
            </div>
            <div className='carousel-subtext'>Where Extraordinary Moments Meet Your Expectations</div>
          </div>
          <div className="carousel-item" data-bs-interval="4000">
            <img src="https://www.samhi.co.in/GIF/Hero-Header-Banner/hyatt-place-gurgaon.jpg" className="d-block w-100" alt="Image 3" />
            <div className="carousel-text">Luxury Elevated <br />
            </div>
            <div className='carousel-subtext'>Experiencing Opulence Like Never Before</div>
          </div>
        </div>
        <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
          <span className="carousel-control-prev-icon" aria-hidden="true"></span>
          <span className="visually-hidden">Previous</span>
        </button>
        <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
          <span className="carousel-control-next-icon" aria-hidden="true"></span>
          <span className="visually-hidden">Next</span>
        </button>
      </div>



      <div>
        <div className="search-filters">
          <select value={selectedCity} onChange={(e) => setSelectedCity(e.target.value)}>
            <label className='search-filter-label'>City</label>
            <option value="">Select a city</option>
            {cities.map(city => (
              <option key={city} value={city}>{city}</option>
            ))}
          </select>
          <label className='search-filter-label'>Check-In-Date</label>
          <DatePicker
            selected={checkInDate}
            onChange={(date) => handleDateChange(date, 'checkIn')}
            minDate={new Date()} // Prevent past dates
            selectsStart
            startDate={checkInDate}
            endDate={checkOutDate}

          />
          <label className='search-filter-label'>Check-out-Date</label>
          <DatePicker
            selected={checkOutDate}
            onChange={(date) => handleDateChange(date, 'checkOut')}
            minDate={checkInDate}

          />

          <select
            value={hotelType}
            onChange={(e) => setHotelType(e.target.value)}
          >
            <option value="TWO_STAR">Two Star</option>
            <option value="THREE_STAR">Three Star</option>
            <option value="FOUR_STAR">Four Star</option>
            <option value="FIVE_STAR">Five Star</option>
          </select>

          <button className='search-button' onClick={handleSearch} style={{ width: '70px', height: '40px' }}>Search</button>
        </div>
      </div>
    </>
  );
}

export default LandingPage;
