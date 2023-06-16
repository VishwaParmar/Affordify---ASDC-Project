import { makeStyles } from "@material-ui/core";
import { minHeight,maxw } from "@mui/system";

const useStyles=makeStyles((theme)=>({
    icon:{
        marginRight:'50px',

    },
    button:{
        marginTop:'80px',
    },
    card:{
        height:'100%',
        width:'auto',
        display:'flex',
        alignItems:"center",
        flexDirection:'column'

    }
    ,
    cardMedia:{
        width:'100px',
        height:'100px'

    },
    cardContent:{
        flexGrow:1
    }
}));

export default useStyles;
