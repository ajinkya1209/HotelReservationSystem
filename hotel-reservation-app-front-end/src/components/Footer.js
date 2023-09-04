// Footer.js
import React from 'react';
import { Link } from "react-router-dom";
import './Footer.css';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-row">
        <div className="footer-column">
          <h4>Hotel Locations</h4>
          <ul>
            <li>Mumbai</li>
            <li>Pune </li>
            <li>Banglore </li>
            <li>Delhi</li>
            
          </ul>
        </div>
        <div className="footer-column">
          <h4>Quick Links</h4>
          <ul>
            <Link className="nav-link active"  to="/">Home</Link>
                            
            <Link className="nav-link" to="/about">About</Link>
          </ul>
        </div>
        <div className="footer-column">
          <h4>Contact Details</h4>
          <p>Email: reyansegrand@example.com</p>
          <p>Phone: +1234567890</p>
        </div>
        <div className="footer-column">
          <h4>Types of Hotels</h4>
          <ul>
            <li>Luxury Hotel</li>
            <li>Resort Hotels</li>
            <li>Beach Hotels</li>
            <li>Delux Hotels</li>
          </ul>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
