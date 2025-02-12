import {Container} from "@mui/material";
import {CssBaseline} from "@mui/material";
import '@mui/material/styles/styled';
import React from "react";
import {Route, Routes} from "react-router-dom";
import AlertAppBar from "./commons/AlertAppBar";
import ErrorHandler from "./commons/errorHandler/ErrorHandler";
import AlertFooter from "./commons/AlertFooter";
import AlertMap from "./features/AlertMap"

function App() {
    return (
        <React.Fragment>
            <ErrorHandler>
                <CssBaseline />
                <AlertAppBar />
                <Container sx={{paddingTop: "4em"}}>
                    <Routes>
                        <Route path="/" element={<AlertMap />} />
                    </Routes>
                </Container>
                <AlertFooter />
            </ErrorHandler>
        </React.Fragment>
    );
}

export default App;
