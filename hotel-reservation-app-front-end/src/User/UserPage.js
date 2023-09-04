import { useEffect, useState } from "react";
import { useHistory } from 'react-router-dom';

import './UserPage.css';
import MyBookings from "./MyBookings";
import UserProfile from "./UserProfile";

const UserPage = () => {
  const [authenticated, setAuthenticated] = useState(null);

  useEffect(() => {
    const loggedInUser = localStorage.getItem("authenticated");
    if (loggedInUser) {
      setAuthenticated(loggedInUser);
    }
  }, []);

  const history = useHistory();

  const handleLogout = () => {
    localStorage.removeItem("authenticated");
    setAuthenticated(null);
    history.push("/");
    alert("Thank You, Logged Out");
  };
  const [activeLink, setActiveLink] = useState('');

  const handleLinkClick = (link) => {
    setActiveLink(link);
  };

  return (
    <>
    <div>
        <button className="logout-button" onClick={handleLogout}>Logout</button>
    </div>    
        <div className='user-page-img'>
            <img src="https://img.freepik.com/free-photo/abstract-grunge-decorative-relief-navy-blue-stucco-wall-texture-wide-angle-rough-colored-background_1258-28311.jpg?w=2000" alt="Welcome" />
            <div className="floating-text">Welcome, Adesh Wakde</div>
        </div>
    <div className="user-page-container">
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
      <div className="main-content">
        <p>Welcome to your Dashboard</p>
        {activeLink === '/UserProfile' && <UserProfile/>}
        {activeLink === '/MyBookings' && <MyBookings />}
        
      </div>
    </div>
    </>
  );
};

export default UserPage;