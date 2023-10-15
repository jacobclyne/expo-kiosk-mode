import { NativeModules } from 'react-native';

const { KioskMode } = NativeModules;

export default {
    enableKioskMode: () => {
        return KioskMode.enableKioskMode();
    },
    disableKioskMode: () => {
        return KioskMode.disableKioskMode();
    },
};
