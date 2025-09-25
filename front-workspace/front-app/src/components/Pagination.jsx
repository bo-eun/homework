import React, { useMemo } from 'react';

/*
    page : 현재 페이지
    totalRows : 전체 row 수
    movePage : 현제 페이지를 인자로 받아 페이지 이동을 실행하는 함수
    pagePerRows : 한 페이지 당 보여줄 row 수
    blockPerCount : 한 블록에 보여주는 페이지 개수
*/
function Pagination({ page, totalRows, movePage, pagePerRows = 10, blockPerCount = 10 }) {

    // 리턴 타입이 객체이기 떄문에.. 함수로 호출하지 않는다...
    const calculatePageData = useMemo(() => {
        if (!totalRows || totalRows == 0 || !pagePerRows || pagePerRows <= 0) {
            return { totalPage: 0, totalBlock: 0, nowBlock: 0, }
        }

        const totalPage = Math.ceil(totalRows / pagePerRows);
        const nowBlock = Math.floor(page / blockPerCount);
        const totalBlock = Math.ceil(totalPage / blockPerCount);

        return { totalPage, nowBlock, totalBlock };

    }, [totalRows, pagePerRows, page, blockPerCount]);

    const renderPage = () => {
        const { totalPage, nowBlock, totalBlock } = calculatePageData;

        if (totalPage == 0) {
            return null
        }

        const pageHTML = [];

        // 처음으로 가기
        let isDisabled = page === 0;
        pageHTML.push(
            <li key="first" className={`page-item ${isDisabled ? 'disabled' : ''}`}>
                <a className='page-link' href="#" onClick={(e) => movePage(0)}>처음</a>
            </li>
        )

        // 이전 블럭 가기
        isDisabled = nowBlock <= 0;
        const firstPage = (nowBlock * blockPerCount) - 1;

        pageHTML.push(
            <li key="prev" className={`page-item`}>
                <a className='page-link' href="#" onClick={() => movePage(firstPage)}>이전</a>
            </li>
        )

        // 페이지 번호 그리기
        for (let i = 0; i < blockPerCount; i++) {
            let isActive = "";
            const pageNum = (nowBlock * blockPerCount) + i;

            if (page === pageNum) {
                isActive = 'active'
            }

            pageHTML.push(
                <li key={pageNum} className={`page-item ${isActive}`}>
                    <a className='page-link' href="#" onClick={() => movePage(pageNum)}>{pageNum + 1}</a>
                </li>
            )

            // 불필요한 페이지 안만들기
            if (totalPage == 0 || totalPage == (pageNum + 1)) {
                break;
            }
        }

        // 다음 블럭 가기
        isDisabled = (nowBlock + 1) >= totalBlock;
        const pageNum = (nowBlock + 1) * blockPerCount;

        pageHTML.push(
            <li key="next" className={`page-item ${isDisabled ? 'disabled' : ''}`}>
                <a className='page-link' href="#" onClick={() => movePage(pageNum)}>다음</a>
            </li>
        )

        // 마지막 페이지
        isDisabled = totalPage === (page + 1);
        console.log('totalPage', totalPage)
        const lastPageNum = totalPage - 1;
        pageHTML.push(
            <li key="last" className={`page-item ${isDisabled ? 'disabled' : ''}`}>
                <a className='page-link' href="#" onClick={() => movePage(lastPageNum)}>마지막</a>
            </li>
        )

        return pageHTML;
    }

    return (
        <>
            <nav aria-label="Page navigation">
                <ul className="pagination justify-content-center">
                    {renderPage()}
                </ul>
            </nav>
        </>
    );
}

export default Pagination;