import React, {useEffect, useState} from 'react';
import {Route, Routes, useNavigate} from 'react-router-dom';
import Appbar from '../components/appbar/Appbar';
import ListForms from '../screens/forms/ListForms';
import AddForm from '../screens/forms/CreateForm';
import PreviewForm from '../features/forms/PreviewForm';
import EditFormScreen from '../screens/forms/EditFormScreen';
import ViewForm from '../features/forms/ViewForm';
import FormSubmissionFeedback from '../features/forms/FormSubmissionFeedback';
import ReleaseNotes from '../components/releaseNotes/ReleaseNotes';
import FormUnauthorized from '../features/forms/FormUnauthorized';

const AppRouter = ({signOut, user}) => {
  const [formId, setFormId] = useState();
  const navigate = useNavigate();
  useEffect(() => {
   // navigate('/forms/home')

    const formId = localStorage.getItem('formId');
    if (formId) {
      localStorage.setItem('userEmail', user?.email);
      setFormId(formId);
      navigate(`/${formId}`);
    }

  }, []);


  return (
    <>
      {window.location.pathname !== '/release-notes' &&
        window.location.pathname !== '/form/feedback' &&
        !formId && <Appbar signOut={signOut} user={user} />}{' '}
      <Routes>
        {formId && (
          <>
            <Route exact path="/:id" element={<ViewForm />} />
            <Route exact path="/form/:id" element={<ViewForm />} />
            <Route
              exact
              path="/form/feedback"
              element={<FormSubmissionFeedback />}
            />
          </>
        )}
        {!formId && (
          <>
            <Route exact path="/form/:id" element={<ViewForm />} />
            <Route
              exact
              path="/form/feedback"
              element={<FormSubmissionFeedback />}
            />
            <Route exact path="/*" element={<ListForms />} />
            <Route exact path="/forms" element={<ListForms user={user} />} />
            <Route
              exact
              path="/create-form"
              element={<AddForm user={user} />}
            />
            <Route
              exact
              path="/create-form/:name"
              element={<AddForm user={user} />}
            />
            <Route exact path="/view-form/:id" element={<PreviewForm />} />
            <Route exact path="/view-form" element={<PreviewForm />} />
            <Route
              exact
              path="/edit-form/:id"
              element={<EditFormScreen user={user} />}
            />
            <Route exact path="/unauthorized" element={<FormUnauthorized />} />
            <Route exact path="/release-notes" element={<ReleaseNotes />} />
            
          </>
        )}
      </Routes>
      {/* <Footer /> */}
    </>
  );
};

export default AppRouter;
