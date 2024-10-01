import React, {useEffect, useState,useRef} from 'react';
import axios from 'axios';
import { Route, Routes } from 'react-router-dom';
import { ThemeProvider } from "@mui/styles";
import { createTheme, responsiveFontSizes } from '@mui/material/styles';
import AppRouter from './routes/AppRouter';
import keycloakJS from './Keycloak';
import './App.css';
// eslint-disable-next-line import/no-unresolved
import '@aws-amplify/ui-react/styles.css';
import ViewForm from './features/forms/ViewForm';
import FormSubmissionFeedback from './features/forms/FormSubmissionFeedback';

function App() {
  const [user, setUser] = useState();

  let theme = createTheme();
theme = responsiveFontSizes(theme);


  const isRun = useRef(false);

  const initialAuth = async () => {
    try {
      const authenticated = await keycloakJS.init({onLoad: 'login-required'});
      console.log(
        `User is ${authenticated ? 'authenticated' : 'not authenticated'}`,
      );
      if (authenticated) {
        const token = keycloakJS.token;
        axios.interceptors.request.use(
          (axios.defaults.headers = {
            Authorization: 'Bearer ' + token,
          }),
        );
        const profile = await keycloakJS.loadUserProfile();
        console.log(profile, 'profile');
        setUser(profile);
      } else {
        signOut();
      }
    } catch (error) {
      console.log('Failed to initialize adapter:', error);
    }
  };
 

  useEffect(() => {
    if (isRun.current) return;
    isRun.current = true;
    initialAuth();
  }, []);

  const signOut = () => {
    keycloakJS.logout({redirectUri: 'http://localhost:3000/'});
  };

  return (
    user ? (
      <ThemeProvider theme={theme}>

        <AppRouter user={user} signOut={signOut} />
        </ThemeProvider>

    ) : (
      <Routes>
      <Route exact path="/form/:id" element={<ViewForm />} />
      <Route exact path="/form/feedback" element={<FormSubmissionFeedback />} />
    </Routes>
    )
  );
}

export default App;
