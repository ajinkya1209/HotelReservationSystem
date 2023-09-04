import React, { useState } from 'react';
import './Register.css';
import axios from 'axios';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
function Register() {
const [error,setError]=useState('');
const history=useHistory();
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    gender: '',
    age: '',
    address: {
      streetAddress:'',
      city:'',
      state:'',
      zipCode:'',
    },
    contactNo: '',
    email: '',
    password: '',
  });

 

  const handleInputChange = (event, field, nestedField = null) => {
    const value = event.target.value;
    setFormData(prevData => ({
      ...prevData,
      [field]: nestedField ? { ...prevData[field], [nestedField]: value } : value,
    }));
  };


  

  const handleRegister = async (e) => {
    // e.preventDefault();
    try {
      // Simulate registration success for demonstration purposes
      // In reality, you would make an API request to register the user
      // and handle success/failure based on the response
      console.log(formData);
      formData.gender  = formData.gender.toUpperCase();
      
       const response=await axios.post('http://localhost:8080/guest/create-user',formData);
      // Simulate an API response delay  guest/create-user
      
    //  console.log(formData);
      await new Promise(resolve => setTimeout(resolve, 1000));

      const registrationResponse = { success: response.status }; // Simulated API response

      if (registrationResponse.success!==undefined) {
        alert('Registration Successful');
        history.push('/signup');
        // Redirect to login page or handle successful registration
      } else {
        alert('Registration failed. Please try again....');
        setError('Registration failed. Please try again.');
      }
    } catch (error) {
      setError('Registration failed. Please try again.');
    }

  };



  return (
    <div className="register-container">
      <img src="https://content.r9cdn.net/rimg/himg/87/41/9b/ice-415319-100973255-620733.jpg" alt="Register" className="register-image" />
      <div className="register-form">
        <h2>Customer Registration</h2>
        <div className="input-container">
          <span className="icon"><i className="fas fa-user"></i></span>
          <label className="input-label">First Name </label>
          <input
            type="text"
            value={formData.firstName}
            onChange={(e) => handleInputChange(e, 'firstName')}
            placeholder="firstname"
            className='input-field'
          />
        </div>
        <div className="input-container">
          <span className="icon"><i className="fas fa-user"></i></span>
          <label className="input-label">Last Name </label>
          <input
            type="text"
            value={formData.lastName}
            onChange={(e) => handleInputChange(e, 'lastName')}
            placeholder="lastname"
            className='input-field'
          />
        </div>



       <div className="gender-container">
  <span className="icon"><i className="fas fa-venus-mars"></i></span>
  <label className="input-label">Gender </label>
  <div className="radio-options">
    <table className="table">
      <tbody>
        <tr>
          <td>
            <label>
              <input
                type="radio"
                value='MALE'
                checked={formData.gender ==='MALE'}
                onChange={(e) => handleInputChange(e, 'gender')}
              />
              Male
            </label>
          </td>
          <td>
            <label>
              <input
                type="radio"
                value='FEMALE'
                checked={formData.gender === 'FEMALE'}
                onChange={(e) => handleInputChange(e, 'gender')}
              />
              Female
            </label>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>




        <div className="input-container">
          <span className="icon"><i className="fas fa-user"></i></span>
          <label className="input-label">Age </label>
          <input
            type="number"
            value={formData.age}
            onChange={(e) => handleInputChange(e, 'age')}
            placeholder="Age"
            className="input-field"
            min="18" // Set the minimum age to 18
          />
        </div>
        <div className="input-container">
          <span className="icon"><i className="fas fa-user"></i></span>
          <label className="input-label">streetAddress </label>
          <input
            type="text"
            value={formData.address.streetAddress}
            onChange={(e) => handleInputChange(e, 'address', 'streetAddress')}
            placeholder="Address"
            className="input-field"

          />
        </div>

        <div className="input-container">
          <span className="icon"><i className="fas fa-user"></i></span>
          <label className="input-label">city </label>
          <input
             type="text"
            value={formData.address.city}
            onChange={(e) => handleInputChange(e, 'address', 'city')}

            placeholder="city"
            className="input-field"

          />
        </div>

        <div className="input-container">
          <span className="icon"><i className="fas fa-user"></i></span>
          <label className="input-label">state </label>
          <input
            type="text"
            value={formData.address.state}
            onChange={(e) => handleInputChange(e, 'address', 'state')}
            placeholder="Address"
            className="input-field"

          />
        </div>

        <div className="input-container">
          <span className="icon"><i className="fas fa-user"></i></span>
          <label className="input-label">zipCode </label>
          <input
            type="number"
            value={formData.address.zipCode}
            onChange={(e) => handleInputChange(e, 'address', 'zipCode')}
            placeholder="Address"
            className="input-field"

          />
        </div>

        <div className="input-container">
          <span className="icon"><i className="fas fa-phone"></i></span>
          <label className="input-label">Contact Number</label>
          <input
            type="tel" // Use type "tel" for phone numbers
            value={formData.contactNo}
            onChange={(e) => handleInputChange(e, 'contactNo')}
            placeholder="Contact Number"
            className="input-field"
            pattern="[0-9]{10}" // Pattern to match exactly 10 digits
            title="Please enter a 10-digit contact number"
            required
          />
        </div>

        <div className="input-container">
          <span className="icon"><i className="fas fa-envelope"></i></span>
          <label className="input-label">Email </label>
          <input
            type="email"
            value={formData.email}
            onChange={(e) => handleInputChange(e, 'email')}
            placeholder="Email"
            className='input-field'
          />
        </div>

        <div className="input-container ">
          <span className="icon"><i className="fas fa-lock"></i></span>
          <label className="input-label">Password </label>
          <input
            type="password"
            value={formData.password}
            onChange={(e) => handleInputChange(e, 'password')}
            placeholder="Password"
            className='input-field'
          />
        </div>
        <button type="button" onClick={(e) => handleRegister(e)}>Register</button>
        {error && <p className="error-message">{error}</p>}
        <p>Already registered? <a href="/login">Login here</a></p>
      </div>
    </div>
  );
}

export default Register;

