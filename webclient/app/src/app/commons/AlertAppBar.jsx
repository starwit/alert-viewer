import {
    AppBar,
    Container,
    IconButton,
    Toolbar,
    Typography
} from "@mui/material";
import React from "react";
import {useTranslation} from 'react-i18next';
import general from "../assets/images/logo_color.png";

function AlertAppBar() {
    const {t} = useTranslation();
    const DynamicLogo = general;

    return (
        <>
            <Container>
                <AppBar color="secondary">
                    <Toolbar>
                        <IconButton
                            size="large"
                            edge="start"
                            color="inherit"
                            href="./"
                            aria-label="menu"
                            sx={{m: 0, p: 0, mr: 2}}
                        >
                            <img src={DynamicLogo} height={40} alt="Alert Viewer" />
                        </IconButton>
                        <Typography variant="h1" component="div" sx={{flexGrow: 1}}>
                            {t('app.title')}
                        </Typography>
                    </Toolbar>
                </AppBar>
            </Container>
        </>
    );
}

export default AlertAppBar;