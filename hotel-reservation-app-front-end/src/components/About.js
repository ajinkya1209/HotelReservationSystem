import React from "react"
import 'bootstrap/dist/css/bootstrap.min.css';
import './AboutPage.css';

function About() {
  return (
    <div className="about-container">
       <div className="about-section">
        <div className="about-image">
          <img src="https://media.istockphoto.com/id/1049008198/photo/booking-hotel-on-internet-travel-planning.jpg?s=612x612&w=0&k=20&c=xLeYQE8WooOKhZzCYI2OG4Do_Hgxl_CPhIFgcgg4DYo=" alt="About Us" />
        </div>
        <div className="about-description">
          <h1>About Our Hotel Reservation App</h1>
          <p className="app-description">
            Welcome to our centralised hotel booking website! Our app makes it
            easy for you to find the perfect accommodations for your travel
            needs. Whether you're planning a business trip or a vacation, we've
            got you covered.
          </p>
        </div>
      </div>
      <div className="mission-section">
        <div className="mission-description">
          <h2>Our Mission</h2>
          <ul className="mission-statement">
            <li>Our mission is to simplify the hotel reservation and booking
            processes.</li>
            <li>Providing you with a seamless and enjoyable experience.</li>
           <li>We believe that your journey starts with a comfortable stay</li>
            <li>Our app is designed to help you find the best hotels that match
            your preferences and budget.</li>
          </ul>
        </div>
      </div>
      <div className="feature-section">
        <div className="feature-description">
          <h3>Explore Our Wide Range of Hotels</h3>
          <p>
            We offer an extensive selection of hotels to choose from, catering to
            different budgets and preferences. Whether you're looking for luxury
            suites or cozy boutique stays, our app has you covered.
          </p>
        </div>
        <div className="feature-image">
          <img src="https://www.industryfreak.com/wp-content/uploads/2019/01/Collage-Most-Expensive-Hotels-in-the-World.jpg" alt="Feature 1" />
        </div>
      </div>
      <div className="feature-section">
        <div className="feature-image">
          <img src="https://assets.publishing.service.gov.uk/government/uploads/system/uploads/image_data/file/83916/s300_hotel_booking.jpg" alt="Feature 2" />
        </div>
        <div className="feature-description">
          <h3>Easy Booking Process</h3>
          <p>
            Our user-friendly booking process allows you to quickly and
            conveniently reserve your stay. Just a few clicks and you're ready
            to embark on your next adventure.
          </p>
        </div>
        </div>
        {/* <div className="user-benefits-section">
        <h2>User Benefits</h2>
        <ul className="user-benefits-list">
          <li>
            <img src="benefit1-image.jpg" alt="Benefit 1" />
            <p>Finding the Best Hotel Deals</p>
          </li>
          <li>
            <img src="benefit2-image.jpg" alt="Benefit 2" />
            <p>Easy and Convenient Booking Process</p>
          </li>
          <li>
            <img src="benefit3-image.jpg" alt="Benefit 3" />
            <p>Personalized Hotel Recommendations</p>
          </li>
          
        </ul>
      </div> */}
      </div>
   
  );
}

export default About;

        

