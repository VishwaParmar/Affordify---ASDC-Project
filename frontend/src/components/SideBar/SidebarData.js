import React from "react";
import * as FaIcons from "react-icons/fa";
import * as AiIcons from "react-icons/ai";
import * as IoIcons from "react-icons/io";
import * as RiIcons from "react-icons/ri";

export const SidebarData = [
{
	title: "Search",
	path: "/search",
	icon: <AiIcons.AiFillHome />,
	iconClosed: <RiIcons.RiArrowDownSFill />,
	iconOpened: <RiIcons.RiArrowUpSFill />,

},
	{
		title: "WebScrapSearch",
		path: "/webscrapsearch",
		icon: <AiIcons.AiFillHome />,
		iconClosed: <RiIcons.RiArrowDownSFill />,
		iconOpened: <RiIcons.RiArrowUpSFill />,

	},
{
	title: "Categories",
	path: "#",
	icon: <IoIcons.IoIosPaper />,
	iconClosed: <RiIcons.RiArrowDownSFill />,
	iconOpened: <RiIcons.RiArrowUpSFill />,

	subNav: [
	{
		title: "Vegetables",
		path: "/services/vegetables",
		icon: <IoIcons.IoIosPaper />,
		cName: "sub-nav",
	},
	{
		title: "Fruits",
		path: "/services/fruits",
		icon: <IoIcons.IoIosPaper />,
		cName: "sub-nav",
	},
	{
		title: "Meat",
		path: "/services/meat",
		icon: <IoIcons.IoIosPaper />,
	},
	],
},
{
	title: "Favorite",
	path: "/favorite",
	icon: <IoIcons.IoIosPaper/>,
},
{
	title: "Price Chart",
	path: "/chart",
	icon: <FaIcons.FaEnvelopeOpenText />,

	iconClosed: <RiIcons.RiArrowDownSFill />,
	iconOpened: <RiIcons.RiArrowUpSFill />,


},
{
	title: "Contact",
	path: "/contact",
	icon: <FaIcons.FaPhone />,
},
];
