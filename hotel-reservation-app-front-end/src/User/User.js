import React, { useState } from 'react';
import './User.css';

import { useHistory } from 'react-router-dom';
import UserProfile from './UserProfile';
import MyBookings from './MyBookings';



const User = () => {
  const [activeLink, setActiveLink] = useState('');
  

  const handleLinkClick = (link) => {
    setActiveLink(link);
  };
 

  const history = useHistory();

  
 

  return (
    <>
   
    <div className='user-page-img'>
    <img src="https://img.freepik.com/free-photo/abstract-grunge-decorative-relief-navy-blue-stucco-wall-texture-wide-angle-rough-colored-background_1258-28311.jpg?w=2000" alt="Welcome" />
    <div className="floating-text">{"Welcome To Plan My Stay"}</div>
</div>
    <div className="user-page">
      <div className="sidebar">
      <ul className="sidebar-list">
          <li
            className={activeLink === '/UserPage' ? 'active' : ''}
            onClick={() => handleLinkClick('/UserPage')}
            >
            User Profile
          </li>
          <li
            className={activeLink === '/MyBookings' ? 'active' : ''}
            onClick={() => handleLinkClick('/MyBookings')}
            >
            My Bookings
          </li>
          
        </ul>
      </div>
      <div className="content">
        
        {activeLink === '/UserPage' && <UserProfile/>}
        {activeLink === '/MyBookings' && <MyBookings />}
        
      </div>
    </div>
 </>
  );
};



export default User;