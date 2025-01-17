import React from 'react'
import CreatorSettings from './CreatorSettings'
import PatronSettings from './PatronSettings';
import './setting_css/SettingsContainers.css';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Sidebar from "../home_page/Sidebar";


/**
 * Calls the files that holds the function LoginForm and RegistrationForm
 *
 * @returns The header tab of sign in and registration form integrated
 */
function SettingsContainers() {

    const [value, setValue] = React.useState(0)

    const handleChange = (event, newValue) => {
        setValue(newValue);
    }

    function TabPanel(props) {
        const { children, value, index, ...other } = props;

        return (
            <div
                role="tabpanel"
                hidden={value !== index}
                id={`simple-tabpanel-${index}`}
                aria-labelledby={`simple-tab-${index}`}
                {...other}
            >
                {value === index && (
                    <Box>
                        <Typography>{children}</Typography>
                    </Box>
                )}
            </div>
        );
    }

    return (
        <div>
            <Sidebar/>
            <div className='paperStyle'>
                <Paper elevation={20}>
                    <Tabs value={value} onChange={handleChange} centered>
                        <Tab label="Creator Settings" />
                        <Tab label="Patron Settings" />
                    </Tabs>
                    <TabPanel value={value} index={0}>
                        <CreatorSettings/>
                    </TabPanel>
                    <TabPanel value={value} index={1}>
                        <PatronSettings />
                    </TabPanel>
                </Paper>
            </div>
        </div>
    );
}

export default SettingsContainers