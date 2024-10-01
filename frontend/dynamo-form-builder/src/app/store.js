import {configureStore, getDefaultMiddleware} from '@reduxjs/toolkit';
import formsReducer from '../features/forms/formSlice';

const store = configureStore({
  middleware: [
    ...getDefaultMiddleware({
      serializableCheck: false,
    }),
  ],
  reducer: {
    forms: formsReducer,
  },
});

export default store;
